package com.yhtech.sysmanage.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Deity
 * @Date 2021/10/28
 * @Description
 */

@Data
public class PageRespListDTO<T> implements Serializable {
    private static final long serialVersionUID = 1899799926958420979L;

    private Integer pageNo;

    private Integer pageSize;

    private Long totalCount;

    private Integer totalPage;

    private List<T> resultList;

    public PageRespListDTO(Integer pageNo, Integer pageSize, Long totalCount, Integer totalPage, List<T> resultList) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.resultList = resultList;
    }
}
