package com.yhtech.sysmanage.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjl
 * @date 2024/5/26 12:32
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 5423331535299214911L;

    private Long userId;
    private String userName;
}
