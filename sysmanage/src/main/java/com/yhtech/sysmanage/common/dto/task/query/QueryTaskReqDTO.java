package com.yhtech.sysmanage.common.dto.task.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjl
 * @date 2024/5/25 18:59
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryTaskReqDTO implements Serializable {
    private static final long serialVersionUID = -2101509480172425154L;

    private Integer pageNo;
    private Integer pageSize;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 任务名称
     */
    private String taskName;
}
