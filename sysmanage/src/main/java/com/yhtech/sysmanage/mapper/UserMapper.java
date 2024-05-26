package com.yhtech.sysmanage.mapper;

import com.yhtech.sysmanage.mapper.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenjl
 * @date 2024/5/25 18:30
 * @desc
 */
@Mapper
public interface UserMapper {
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(User record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    User selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(User record);


    List<User> selectAll();

}