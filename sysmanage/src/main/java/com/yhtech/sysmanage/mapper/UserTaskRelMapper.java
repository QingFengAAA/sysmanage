package com.yhtech.sysmanage.mapper;

import com.yhtech.sysmanage.mapper.entity.UserTaskRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenjl
 * @date 2024/5/25 18:31
 * @desc
 */
@Mapper
public interface UserTaskRelMapper {
    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserTaskRel record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    UserTaskRel selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UserTaskRel record);

    int batchInsert(@Param("list") List<UserTaskRel> list);

    List<UserTaskRel> selectTaskDataList(@Param("userId") Long userId,
                                         @Param("taskName") String taskName,
                                         @Param("yn") Integer yn);

    int updateYnById(@Param("id") Long id,
                     @Param("yn") Integer yn,
                     @Param("updateUser") Integer updateUser);
}