package com.yhtech.sysmanage.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhtech.sysmanage.common.dto.PageRespListDTO;
import com.yhtech.sysmanage.service.RedisToolsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenjl
 * @date 2024/5/26 9:16
 * @desc
 */
@Service("redisToolsService")
public class RedisToolsServiceImpl implements RedisToolsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisToolsServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 将数据存入Redis缓存，并设置过期时间
     *
     * @param key      缓存键
     * @param value    缓存值
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     */
    public void setData(String key, Object value, long timeout, TimeUnit timeUnit) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, timeout, timeUnit);
    }

    /**
     * 从Redis缓存中获取数据
     *
     * @param key   缓存键
     * @param <T>   泛型
     * @return 缓存中的数据
     */
    public <T> T getData(String key, TypeReference<T> typeReference) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object value = valueOperations.get(key);
        if (value != null) {
            try {
                // 使用 readValue 方法进行反序列化
                String json = objectMapper.writeValueAsString(value);
                return objectMapper.readValue(json, typeReference);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 使用游标进行分页
     * @param pattern 键的匹配模式
     * @param pageSize 每页大小
     * @param pageNo 页码（从1开始）
     * @param clazz 指定对象的类型
     * @return 分页结果和新的游标ID
     */
    public <T> PageRespListDTO<T> scanWithCursor(String pattern, int pageSize, int pageNo, Class<T> clazz) {
        LOGGER.info("Scanning with pattern: {}", pattern);

        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(1000).build();
        Cursor<byte[]> cursor = redisTemplate.executeWithStickyConnection(redisConnection -> redisConnection.scan(options));

        List<T> resultList = new ArrayList<>();
        long totalCount = 0;
        int start = (pageNo - 1) * pageSize;
        int end = start + pageSize;

        // 模糊匹配模式
        Pattern keyRegex = Pattern.compile(".*研.*");

        while (cursor.hasNext()) {
            byte[] key = cursor.next();
            String keyString = new String(key, StandardCharsets.UTF_8);
            LOGGER.info("Found key: {}", keyString);

            Matcher matcher = keyRegex.matcher(keyString);
            if (matcher.matches()) {
                LOGGER.info("Key matches pattern: {}", keyString);
                Object value = redisTemplate.opsForValue().get(keyString);
                if (value != null) {
                    LOGGER.info("Value found for key {}: {}", keyString, value);
                    if (totalCount >= start && totalCount < end) {
                        resultList.add(clazz.cast(value));
                    }
                    totalCount++;
                    if (resultList.size() >= pageSize) {
                        break;
                    }
                }
            }
        }

        int totalPage = (int) Math.ceil((double) totalCount / pageSize);

        LOGGER.info("Scan result - totalCount: {}, totalPage: {}", totalCount, totalPage);

        return new PageRespListDTO<>(pageNo, pageSize, totalCount, totalPage, resultList);
    }

    /**
     * 生成缓存键，结合用户ID和任务名称
     *
     * @param userId   用户ID
     * @param taskName 任务名称
     * @return 缓存键
     */
    public String generateCacheKey(Long userId, String taskName) {
        StringBuilder cacheKey = new StringBuilder("yh:sysmanage:task:");

        if (userId != null) {
            cacheKey.append(userId).append(":");
        } else {
            cacheKey.append("*:");
        }

        if (taskName != null && !taskName.isEmpty()) {
            cacheKey.append(taskName).append("*");
        } else {
            cacheKey.append("*");
        }

        return cacheKey.toString();
    }

}
