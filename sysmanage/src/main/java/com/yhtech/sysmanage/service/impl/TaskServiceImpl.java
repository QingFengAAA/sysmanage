package com.yhtech.sysmanage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhtech.sysmanage.common.constant.BaseConstant;
import com.yhtech.sysmanage.common.dto.PageRespListDTO;
import com.yhtech.sysmanage.common.dto.task.create.CreateTaskReqDTO;
import com.yhtech.sysmanage.common.dto.task.delete.DeleteTaskReqDTO;
import com.yhtech.sysmanage.common.dto.task.query.QueryTaskDTO;
import com.yhtech.sysmanage.common.dto.task.query.QueryTaskReqDTO;
import com.yhtech.sysmanage.mapper.TaskMapper;
import com.yhtech.sysmanage.mapper.UserMapper;
import com.yhtech.sysmanage.mapper.UserTaskRelMapper;
import com.yhtech.sysmanage.mapper.entity.Task;
import com.yhtech.sysmanage.mapper.entity.User;
import com.yhtech.sysmanage.mapper.entity.UserTaskRel;
import com.yhtech.sysmanage.service.RedisToolsService;
import com.yhtech.sysmanage.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author chenjl
 * @date 2024/5/25 19:04
 * @desc
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);


    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserTaskRelMapper userTaskRelMapper;
    @Autowired
    private RedisToolsService redisToolsService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 创建任务数据
     * @param request
     * @return
     */
    @Override
    public Boolean taskCreateData(CreateTaskReqDTO request) {
        // 创建任务
        Task task = Task.builder()
                .taskName(request.getTaskName())
                .taskDesc(request.getTaskDesc())
                .companyId(BaseConstant.DIGIT_LONG_ONE)
                .createAt(new Date())
                .updateAt(new Date())
                .createUser(BaseConstant.DIGIT_LONG_ONE)
                .updateUser(BaseConstant.DIGIT_LONG_ONE).build();
        taskMapper.insertSelective(task);
        // 插入任务关联数据
        UserTaskRel build = UserTaskRel.builder()
                .userId(BaseConstant.DIGIT_LONG_ONE)
                .taskId(task.getId())
                .yn(BaseConstant.DIGIT_INT_ONE)
                .companyId(BaseConstant.DIGIT_LONG_ONE)
                .createAt(new Date())
                .updateAt(new Date())
                .createUser(BaseConstant.DIGIT_LONG_ONE)
                .updateUser(BaseConstant.DIGIT_LONG_ONE).build();
        userTaskRelMapper.insertSelective(build);
        // 添加到缓存
        String key = redisToolsService.generateCacheKey(BaseConstant.DIGIT_LONG_ONE, request.getTaskName());
        // 查询用户信息
        User user = userMapper.selectByPrimaryKey(BaseConstant.DIGIT_LONG_ONE);
        QueryTaskDTO taskData = QueryTaskDTO.builder()
                .id(build.getId())
                .taskName(request.getTaskName())
                .taskDesc(request.getTaskDesc())
                .userName(user.getUserName()).build();
        redisToolsService.setData(key, taskData, 30, TimeUnit.MINUTES); // 设置缓存时间为30分钟
        return true;
    }

    /**
     * 查询任务数据
     * @param request
     * @return
     */
    @Override
    public PageRespListDTO taskQueryData(QueryTaskReqDTO request) {
        // 尝试从缓存中获取任务数据
        String cacheKey = redisToolsService.generateCacheKey(request.getUserId(), request.getTaskName());
        LOGGER.info("TaskServiceImpl ==> taskQueryData cache key:{}", cacheKey);
        /*TypeReference<PageRespListDTO<QueryTaskDTO>> typeReference = new TypeReference<PageRespListDTO<QueryTaskDTO>>() {};*/
        PageRespListDTO<QueryTaskDTO> cachedResult = redisToolsService.scanWithCursor(cacheKey, request.getPageSize(),
                request.getPageNo(), QueryTaskDTO.class);
        LOGGER.info("TaskServiceImpl ==> taskQueryData cache data:{}", cachedResult);
        if (cachedResult != null && !CollectionUtils.isEmpty(cachedResult.getResultList())) {
            LOGGER.info("TaskServiceImpl ==> taskQueryData cache data:{}", cachedResult);
            return cachedResult;
        }
        PageInfo<UserTaskRel> pageInfo = PageHelper.startPage(
                        ObjectUtils.isEmpty(request.getPageNo()) ? BaseConstant.DIGIT_INT_ONE : request.getPageNo(),
                        ObjectUtils.isEmpty(request.getPageSize()) ? BaseConstant.DIGIT_INT_TEN : request.getPageSize())
                .doSelectPageInfo(
                        () -> userTaskRelMapper.selectTaskDataList(request.getUserId(), request.getTaskName(),
                                BaseConstant.DIGIT_INT_ONE)
                );
        if (ObjectUtils.isEmpty(pageInfo) || CollectionUtils.isEmpty(pageInfo.getList())) {
            return new PageRespListDTO(BaseConstant.DIGIT_INT_ZERO, BaseConstant.DIGIT_INT_ZERO,
                    BaseConstant.DIGIT_LONG_ZERO, BaseConstant.DIGIT_INT_ZERO, Collections.emptyList());
        }
        List<QueryTaskDTO> taskList = pageInfo.getList().stream()
                .map(p -> QueryTaskDTO.builder()
                        .id(p.getId())
                        .taskName(p.getTask().getTaskName())
                        .taskDesc(p.getTask().getTaskDesc())
                        .userName(p.getUser().getUserName()).build())
                .collect(Collectors.toList());
        return new PageRespListDTO(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(),
                pageInfo.getPages(), taskList);
    }

    /**
     * 删除任务数据
     * @param request
     * @return
     */
    @Override
    public Boolean taskDeleteData(DeleteTaskReqDTO request) {
        // 逻辑删除任务
        int updateRet = userTaskRelMapper.updateYnById(request.getId(), BaseConstant.DIGIT_INT_ZERO,
                BaseConstant.DIGIT_INT_ONE);
        return BaseConstant.DIGIT_INT_ZERO.compareTo(updateRet) < 0;
    }
}
