package com.yhtech.sysmanage.service.impl;

import com.yhtech.sysmanage.common.dto.CommonDataDTO;
import com.yhtech.sysmanage.common.dto.CommonListDTO;
import com.yhtech.sysmanage.mapper.UserMapper;
import com.yhtech.sysmanage.mapper.entity.User;
import com.yhtech.sysmanage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenjl
 * @date 2024/5/26 12:31
 * @desc
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户列表数据(此处仅仅为了简单获取用户信息， 方便页面演示效果， 实际上此处应该也要缓存用户信息)
     * @return
     */
    @Override
    public CommonListDTO queryUserList() {
        List<User> users = userMapper.selectAll();
        if (CollectionUtils.isEmpty(users)) {
            return new CommonListDTO();
        }
        List<CommonDataDTO> commonDataList = users.stream()
                .map(p -> CommonDataDTO.builder()
                        .code(p.getId())
                        .value(p.getUserName()).build())
                .collect(Collectors.toList());
        return new CommonListDTO(commonDataList);
    }
}
