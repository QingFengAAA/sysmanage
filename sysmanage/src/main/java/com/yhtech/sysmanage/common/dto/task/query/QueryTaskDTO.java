package com.yhtech.sysmanage.common.dto.task.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjl
 * @date 2024/5/25 19:00
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryTaskDTO implements Serializable {
    private static final long serialVersionUID = 5716350863492892154L;

    /**
     * 任务关联主键ID
     */
    private Long id;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务描述
     */
    private String taskDesc;
    /**
     * 任务待办人名称
     */
    private String userName;
}
