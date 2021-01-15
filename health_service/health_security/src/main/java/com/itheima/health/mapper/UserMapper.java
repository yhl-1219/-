package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.User;
import com.itheima.health.vo.RoleVO;
import com.itheima.health.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from t_user where username = #{username}")
    UserVO findUserInfoByUsername(@Param("username") String username);

    @Select("select tr.* from t_role tr , t_user_role tur where tr.id = tur.role_id and tur.user_id = #{uid}")
    Set<RoleVO> findrolesByUid(@Param("uid") Integer uid);

    @Select("select tp.*  from t_permission tp , t_role_permission trp where tp.id = trp.permission_id and trp.role_id = #{rid}")
    Set<Permission> findPermssionsByRoleId(@Param("rid") Integer rid);

}
