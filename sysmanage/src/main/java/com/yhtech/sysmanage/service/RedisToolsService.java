package com.yhtech.sysmanage.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yhtech.sysmanage.common.dto.PageRespListDTO;

import java.util.concurrent.TimeUnit;

/**
 * @author chenjl
 * @date 2024/5/26 9:16
 * @desc
 */
public interface RedisToolsService {

    /**
     * 将数据存入Redis缓存，并设置过期时间
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    void setData(String key, Object value, long timeout, TimeUnit timeUnit);

    /**
     * 从Redis缓存中获取数据
     * @param key
     * @param typeReference
     * @return
     * @param <T>
     */
    <T> T getData(String key, TypeReference<T> typeReference);

    /**
     * 使用游标进行分页
     * @param pattern
     * @param pageSize
     * @param pageNo
     * @param clazz
     * @return
     * @param <T>
     */
    <T> PageRespListDTO<T> scanWithCursor(String pattern, int pageSize, int pageNo, Class<T> clazz);

    /**
     * 生成缓存键，结合用户ID和任务名称
     * @param userId
     * @param userName
     * @return
     */
    String generateCacheKey(Long userId, String userName);
}
