package com.yhtech.sysmanage.service;

import com.yhtech.sysmanage.common.dto.PageRespListDTO;
import com.yhtech.sysmanage.common.dto.task.create.CreateTaskReqDTO;
import com.yhtech.sysmanage.common.dto.task.delete.DeleteTaskReqDTO;
import com.yhtech.sysmanage.common.dto.task.query.QueryTaskReqDTO;

/**
 * @author chenjl
 * @date 2024/5/25 19:03
 * @desc
 */
public interface TaskService {

    /**
     * 创建任务数据
     * @param createTaskReqDTO
     * @return
     */
    Boolean taskCreateData(CreateTaskReqDTO createTaskReqDTO);

    /**
     * 查询任务数据
     * @param queryTaskReqDTO
     * @return
     */
    PageRespListDTO taskQueryData(QueryTaskReqDTO queryTaskReqDTO);

    /**
     * 删除任务数据
     * @param deleteTaskReqDTO
     * @return
     */
    Boolean taskDeleteData(DeleteTaskReqDTO deleteTaskReqDTO);
}
