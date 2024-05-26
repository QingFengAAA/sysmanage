package com.yhtech.sysmanage.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author chenjl
 * @date 2024/5/25 18:30
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 性别 1-男 2-女
    */
    private Integer userGender;

    /**
    * 用户年龄
    */
    private Date userBirthday;

    /**
    * 用户身份证号
    */
    private String userCert;

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
}