package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.mapper.RoleMapper;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Menu;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/19 7:47 下午
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public PageResult findPage(QueryPageBean pageBean) {
        String queryString = pageBean.getQueryString();
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (queryString != null && queryString.length() > 0) {
            wrapper.like("name", queryString).or().like("keyword", queryString);
        }
        Page<Role> page = page(new Page<>(pageBean.getCurrentPage(), pageBean.getPageSize()), wrapper);
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Permission> findAllPermission() {
        return baseMapper.findAllPermission();
    }

    @Override
    public List<Menu> findAllMenu() {
        return baseMapper.findAllMenu();
    }

    @Override
    public Integer[] findPermissionInfoById(Integer id) {
        return baseMapper.findPermissionInfoById(id).toArray(new Integer[0]);
    }

    @Override
    public Integer[] findMenuInfoById(Integer id) {
        return baseMapper.findMenuInfoById(id).toArray(new Integer[0]);
    }

    @Override
    public boolean addOrUpdateRole(Role role) {
        if (role.getId() != null) {
            baseMapper.deletePermissionByRoleId(role.getId());
        }
        saveOrUpdate(role);
        List<Integer> permissionIds = role.getPermissionIds();
        for (Integer permissionId : permissionIds) {
            baseMapper.insertPermissionByRoleIdAndPermissionId(role.getId(), permissionId);
        }
        List<Integer> menuIds = role.getMenuIds();
        for (Integer menuId : menuIds) {
            baseMapper.insertMenuByRoleIdAndMenuId(role.getId(), menuId);
        }
        return true;
    }

    @Override
    public boolean deleteRoleById(Integer id) {
        baseMapper.deletePermissionByRoleId(id);
        removeById(id);
        return true;
    }


}
