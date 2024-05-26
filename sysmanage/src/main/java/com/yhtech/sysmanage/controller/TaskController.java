package com.yhtech.sysmanage.controller;

import com.yhtech.sysmanage.common.dto.PageRespListDTO;
import com.yhtech.sysmanage.common.dto.Result;
import com.yhtech.sysmanage.common.dto.task.create.CreateTaskReqDTO;
import com.yhtech.sysmanage.common.dto.task.delete.DeleteTaskReqDTO;
import com.yhtech.sysmanage.common.dto.task.query.QueryTaskReqDTO;
import com.yhtech.sysmanage.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjl
 * @date 2024/5/25 17:34
 * @desc
 */
@RestController
@RequestMapping(value = "/sysmanage/tasks")
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     * 创建任务数据
     * @param createTaskReqDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create/data", method = RequestMethod.POST)
    public Result<Object> taskCreateData(@RequestBody CreateTaskReqDTO createTaskReqDTO) {
        try {
            return new Result(taskService.taskCreateData(createTaskReqDTO));
        } catch (Exception e) {
            LOGGER.error("TaskController  ==> taskCreateData failed", e.getMessage());
            return new Result("0000001", "响应失败", e.getMessage());
        }
    }

    /**
     * 查询任务数据
     * @param userId
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/dataList", method = RequestMethod.GET)
    public Result<PageRespListDTO> taskQueryDataList(@RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                     @RequestParam(value = "userId", required = false) Long userId,
                                                     @RequestParam(value = "userName", required = false) String userName) {
        try {
            QueryTaskReqDTO build = QueryTaskReqDTO.builder()
                    .pageNo(pageNo)
                    .pageSize(pageSize)
                    .taskName(userName)
                    .userId(userId).build();
            return new Result(taskService.taskQueryData(build));
        } catch (Exception e) {
            LOGGER.error("TaskController  ==> taskQueryDataList failed", e.getMessage());
            return new Result("0000002", "响应失败", e.getMessage());
        }
    }

    /**
     * 删除任务数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/data", method = RequestMethod.DELETE)
    public Result<Boolean> taskDeleteData(@RequestParam("id") Long id) {
        try {
            DeleteTaskReqDTO build = DeleteTaskReqDTO.builder().id(id).build();
            return new Result(taskService.taskDeleteData(build));
        } catch (Exception e) {
            LOGGER.error("TaskController  ==> taskDeleteData failed", e.getMessage());
            return new Result("0000003", "响应失败", e.getMessage());
        }
    }
}
