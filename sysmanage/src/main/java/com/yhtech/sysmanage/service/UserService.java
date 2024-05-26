package com.yhtech.sysmanage.service;

import com.yhtech.sysmanage.common.dto.CommonListDTO;

/**
 * @author chenjl
 * @date 2024/5/26 12:30
 * @desc
 */
public interface UserService {

    /**
     * 查询用户列表数据
     * @return
     */
    CommonListDTO queryUserList();
}
