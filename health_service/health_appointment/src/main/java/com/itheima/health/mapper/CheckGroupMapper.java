package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangweili
 */
public interface CheckGroupMapper extends BaseMapper<CheckGroup> {

    @Insert("insert into t_checkgroup_checkitem values (#{groupId},#{checkItemId})")
    void addCheckGroupAndCheckItemIds(@Param("groupId") Integer groupId,@Param("checkItemId") Integer checkItemId);

    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id = #{id}")
    List<Integer> findCheckItemIdsByGroupId(@Param("id") Integer id);
    
    @Delete("delete from t_checkgroup_checkitem where checkgroup_id = #{id}")
    void deleteCheckGroupAndCheckItemIdsById(@Param("id") Integer id);


}
