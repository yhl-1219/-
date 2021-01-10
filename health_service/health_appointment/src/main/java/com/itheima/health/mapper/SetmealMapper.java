package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangweili
 */
public interface SetmealMapper extends BaseMapper<Setmeal> {

    @Insert("insert into t_setmeal_checkgroup values (#{setmealId},#{groupId})")
    void addSetmealAndCheckGroupById(@Param("setmealId") Integer setmealId, @Param("groupId") Integer groupId);

    @Delete("delete from t_setmeal_checkgroup where setmeal_id = #{setmealId}")
    void deleteSetmealAndCheckGroupById(@Param("setmealId") Integer setmealId);

    @Select("select checkgroup_id from t_setmeal_checkgroup where setmeal_id = #{setmealId}")
    List<Integer> findGroupIdsBySetmealId(@Param("setmealId") Integer id);
}
