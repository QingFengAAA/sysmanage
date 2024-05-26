package com.yhtech.sysmanage.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjl
 * @date 2024/5/26 12:35
 * @desc
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonDataDTO implements Serializable {
    private static final long serialVersionUID = 6390637807398347457L;

    private Long code;
    private String value;
}
