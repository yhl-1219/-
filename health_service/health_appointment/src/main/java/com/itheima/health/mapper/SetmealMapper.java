package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
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

    @Select("select checkgroup_id from t_setmeal_checkgroup where setmeal_id = #{id}")
    List<Integer> findGroupIdsBySetmealId(@Param("id") Integer id);

    @Select("select t_checkgroup.* from t_setmeal,t_setmeal_checkgroup,t_checkgroup where t_setmeal.id = #{id} and t_setmeal_checkgroup.setmeal_id = t_setmeal.id and t_checkgroup.id = t_setmeal_checkgroup.checkgroup_id and t_checkgroup.is_delete = 0 and t_setmeal.is_delete = 0")
    List<CheckGroup> findCheckGroupIdsBySetmealId(@Param("id") Integer id);

    @Select("select * from t_checkitem,t_checkgroup_checkitem,t_checkgroup where t_checkitem.id = t_checkgroup_checkitem.checkitem_id and t_checkgroup_checkitem.checkgroup_id = t_checkgroup.id and t_checkgroup.is_delete = 0 and t_checkitem.is_delete = 0 and t_checkgroup.id = #{id}")
    List<CheckItem> findCheckItemsByGroupId(@Param("id") Integer id);
}
