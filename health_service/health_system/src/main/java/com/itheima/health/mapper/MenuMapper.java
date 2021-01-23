package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    @Select("select tm.* from t_menu tm,  t_role_menu trm where trm.menu_id=tm.id and trm.role_id = #{rid}")
    List<Menu> findMenuByRoleId(@Param("rid") Integer id);

    @Delete("delete from t_role_menu where menu_id=#{id}")
    void deleteMenuByRoleId(@Param("id") Integer id);

}
