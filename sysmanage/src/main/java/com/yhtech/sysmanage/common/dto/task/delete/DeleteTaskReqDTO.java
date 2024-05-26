package com.yhtech.sysmanage.common.dto.task.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjl
 * @date 2024/5/25 18:55
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTaskReqDTO implements Serializable {
    private static final long serialVersionUID = 7338300632774439912L;

    /**
     * 任务关联主键ID
     */
    private Long id;
}
