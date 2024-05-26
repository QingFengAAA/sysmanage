package com.yhtech.sysmanage.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenjl
 * @date 2024/5/26 12:33
 * @desc
 */
@Data
public class CommonListDTO<T> implements Serializable {
    private static final long serialVersionUID = 668091438952675830L;

    private List<T> resultList;

    public CommonListDTO() {
    }

    public CommonListDTO(List<T> resultList) {
        this.resultList = resultList;
    }
}
