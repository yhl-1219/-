package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.mapper.MenuMapper;


import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import com.itheima.health.service.UserService;
import com.itheima.health.utils.changeObjectType.BeanConv;
import com.itheima.health.vo.MenuVO;
import com.itheima.health.vo.RoleVO;
import com.itheima.health.vo.UserVO;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Reference
    private UserService userService;

    @Override
    public Map findAllMenuByUsername(String username) {
        //查找当前用户角色
        UserVO userVO = userService.findUserInfoByUsername(username);
        List<RoleVO> roles = userVO.getRoleVOList();
        //用户的所有菜单
        Set<Menu> menuVOSet = new HashSet<>();
        if (roles != null && roles.size() > 0) {
            for (RoleVO role : roles) {
                List<Menu> list = baseMapper.findMenuByRoleId(role.getId());
                System.out.println("list = " + list);
                menuVOSet.addAll(list);
            }
        }
        //父菜单
        List<Menu> pList = new ArrayList<>();
        //子菜单
        List<Menu> cList = new ArrayList<>();
        //所能跳转的路线
        List<String> urlList = new ArrayList<>();
        //遍历集合
        for (Menu menu : menuVOSet) {
            if (menu.getLinkUrl() != null && !"".equals(menu.getLinkUrl())) {
                urlList.add(menu.getLinkUrl());
            }
            if (menu.getLevel() == 1) {
                pList.add(menu);
            }
            if (menu.getLevel() == 2) {
                cList.add(menu);
            }
        }
        List<MenuVO> list1 = BeanConv.toBeanList(pList, MenuVO.class);
        //父菜单排序
        MyComparator comparator = new MyComparator();
        Collections.sort(list1, comparator);
        for (MenuVO menuVO : list1) {
            List<Menu> list = new ArrayList<>();
            for (Menu menu : cList) {
                if (menu.getParentMenuId().equals(menuVO.getId())) {
                    list.add(menu);
                }
            }
            List<MenuVO> list2 = BeanConv.toBeanList(list, MenuVO.class);
            Collections.sort(list2, comparator);
            menuVO.setChildren(list2);
        }
        Map map = new HashMap();
        map.put("linkUrl", urlList);
        map.put("menu", list1);
        return map;
    }

    public class MyComparator implements Comparator<MenuVO> {
        @Override
        public int compare(MenuVO o1, MenuVO o2) {
            return o1.getPriority() - o2.getPriority();
        }
    }

    @Override
    public PageResult findPage(QueryPageBean pageBean) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        String queryString = pageBean.getQueryString();
        if (queryString != null && queryString.length() > 0) {
            wrapper.like("name", queryString).or().like("linkurl", queryString);
        }
        Page<Menu> page = page(new Page<>(pageBean.getCurrentPage(), pageBean.getPageSize()), wrapper);
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Override
    public boolean addOrUpdateMenu(Menu menu) {
        saveOrUpdate(menu);
        return true;
    }

    @Override
    public boolean deleteMenuById(Integer id) {
        baseMapper.deleteMenuByRoleId(id);
        removeById(id);
        return true;
    }

}
