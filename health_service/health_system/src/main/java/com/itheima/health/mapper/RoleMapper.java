package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/19 7:48 下午
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from t_permission")
    List<Permission> findAllPermission();

    @Select("select t_permission.id from t_role,t_role_permission,t_permission where t_role.id=t_role_permission.role_id and t_role_permission.permission_id=t_permission.id and t_role.id=#{id}")
    List<Integer> findPermissionInfoById(@Param("id") Integer id);
    
    @Delete("delete from t_role_permission where role_id=#{id}")
    void deletePermissionByRoleId(@Param("id") Integer id);
    
    @Insert("insert into t_role_permission values (#{roleId},#{permissionId})")
    void insertPermissionByRoleIdAndPermissionId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
    
}