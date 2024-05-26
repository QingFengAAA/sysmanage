package com.yhtech.sysmanage.common.dto.task.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjl
 * @date 2024/5/25 18:36
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskReqDTO implements Serializable {
    private static final long serialVersionUID = 3248765995986282292L;

    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务描述
     */
    private String taskDesc;
}
