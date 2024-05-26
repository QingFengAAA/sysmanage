package com.yhtech.sysmanage.mapper;

import com.yhtech.sysmanage.mapper.entity.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenjl
 * @date 2024/5/25 18:30
 * @desc
 */
@Mapper
public interface TaskMapper {
    int insertSelective(Task record);

    Task selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Task record);
}