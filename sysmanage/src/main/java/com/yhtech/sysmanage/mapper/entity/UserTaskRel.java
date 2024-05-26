package com.yhtech.sysmanage.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenjl
 * @date 2024/5/25 18:31
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskRel {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 用户ID
    */
    private Long userId;

    /**
    * 任务ID
    */
    private Long taskId;

    /**
    * 删除标识 0-不可用 1-可用
    */
    private Integer yn;

    /**
    * 企业ID
    */
    private Long companyId;

    /**
    * 创建时间
    */
    private Date createAt;

    /**
    * 更新时间
    */
    private Date updateAt;

    /**
    * 创建人
    */
    private Long createUser;

    /**
    * 更新人
    */
    private Long updateUser;

    private User user;
    private Task task;
}