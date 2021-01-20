package com.itheima.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.User;
import com.itheima.health.vo.UserVO;

/**
 * @author wangweili 
 */
public interface UserService extends IService<User> {

    UserVO findUserInfoByUsername(String username);

    PageResult findPage(QueryPageBean pageBean);

    boolean addOrUpdateRole(User user);

    boolean deleteUserById(Integer id);

    Integer[] findRoleInfoById(Integer id);

}
