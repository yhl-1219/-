package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.mapper.UserMapper;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import com.itheima.health.utils.date.DateUtils;
import com.itheima.health.utils.resources.MessageConstant;
import com.itheima.health.vo.RoleVO;
import com.itheima.health.vo.UserVO;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wangweili
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public BCryptPasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserVO findUserInfoByUsername(String username) {
        UserVO userVO = baseMapper.findUserInfoByUsername(username);
        Set<RoleVO> roleVoSet = baseMapper.findrolesByUid(userVO.getId());
        for (RoleVO roleVO : roleVoSet) {
            Set<Permission> permssions = baseMapper.findPermssionsByRoleId(roleVO.getId());
            List<Permission> permissions = new ArrayList<>(permssions);
            roleVO.setPermissionsList(permissions);
        }
        userVO.setRoleVOList(new ArrayList<>(roleVoSet));
        return userVO;
    }

    @Override
    @SneakyThrows
    public PageResult findPage(QueryPageBean pageBean) {
        String queryString = pageBean.getQueryString();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (queryString != null && !"".equals(queryString)) {
            wrapper.like("username", queryString);
        }
        Page<User> page = page(new Page<>(pageBean.getCurrentPage(), pageBean.getPageSize()), wrapper);
        for (User record : page.getRecords()) {
            record.setDate(DateUtils.parseDate2String(record.getBirthday()));
        }
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Override
    public boolean addOrUpdateRole(User user) {
        if (user.getId() != null) {
            baseMapper.deleteRoleById(user.getId());
            if (!user.getPassword().equals(getById(user.getId()).getPassword())) {
                user.setPassword(createPasswordEncoder().encode(user.getPassword()));
            }
        } else {
            user.setPassword(createPasswordEncoder().encode(user.getPassword()));
        }
        saveOrUpdate(user);
        List<Integer> roleIds = user.getRoleIds();
        for (Integer roleId : roleIds) {
            baseMapper.addRoleByIds(roleId, user.getId());
        }
        return true;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        baseMapper.deleteRoleById(id);
        removeById(id);
        return true;
    }

    @Override
    public Integer[] findRoleInfoById(Integer id) {
        return baseMapper.findRoleInfoById(id).toArray(new Integer[0]);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User o = getOne(wrapper);
        if (!createPasswordEncoder().matches(oldPassword, o.getPassword())) {
            throw new RuntimeException(MessageConstant.EDIT_NOW_USER_OLD_PASSWORD_NOT_CORRECT);
        }

        baseMapper.updatePassword(username, createPasswordEncoder().encode(newPassword));
        return true;
    }
}