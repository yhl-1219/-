package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.mapper.UserMapper;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import com.itheima.health.vo.RoleVO;
import com.itheima.health.vo.UserVO;
import org.apache.dubbo.config.annotation.Service;
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
}
