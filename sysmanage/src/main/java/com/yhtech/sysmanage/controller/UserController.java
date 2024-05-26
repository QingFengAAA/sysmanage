package com.yhtech.sysmanage.controller;

import com.yhtech.sysmanage.common.dto.CommonListDTO;
import com.yhtech.sysmanage.common.dto.Result;
import com.yhtech.sysmanage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjl
 * @date 2024/5/26 12:29
 * @desc
 */
@RestController
@RequestMapping(value = "/sysmanage/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/query/dataList", method = RequestMethod.GET)
    public Result<CommonListDTO> userQueryDataList() {
        try {
            return new Result(userService.queryUserList());
        } catch (Exception e) {
            LOGGER.error("UserController  ==> userQueryDataList failed", e.getMessage());
            return new Result("0000002", "响应失败", e.getMessage());
        }
    }
}
