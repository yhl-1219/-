package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.dto.CheckGroupDTO;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.mapper.CheckGroupMapper;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import com.itheima.health.vo.CheckGroupVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangweili
 */
@Service
@Transactional
public class CheckGroupServiceImpl extends ServiceImpl<CheckGroupMapper, CheckGroup> implements CheckGroupService {
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        QueryWrapper<CheckGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            wrapper.like("name", queryPageBean.getQueryString()).or().like("code", queryPageBean.getQueryString());
        }
        Page<CheckGroup> page = page(new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize()), wrapper);
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Override
    public boolean add(CheckGroupDTO checkGroupDTO) {
        saveOrUpdate(checkGroupDTO);
        Integer groupId = checkGroupDTO.getId();
        Integer[] checkitemIds = checkGroupDTO.getCheckitemIds();
        baseMapper.deleteCheckGroupAndCheckItemIdsById(groupId);
        if (checkitemIds != null && checkitemIds.length != 0) {
            for (Integer checkitemId : checkitemIds) {
                baseMapper.addCheckGroupAndCheckItemIds(groupId, checkitemId);
            }
        }
        return true;
    }

    @Override
    public boolean deleteCheckGroupById(Integer id) {
        baseMapper.deleteCheckGroupAndCheckItemIdsById(id);
        CheckGroup checkGroup = new CheckGroup();
        checkGroup.setId(id);
        checkGroup.setIsDelete(1);
        updateById(checkGroup);
        return true;
    }

    @Override
    public CheckGroupVO findCheckItemInfoByGroupId(Integer id) {
        List<Integer> itemIds = baseMapper.findCheckItemIdsByGroupId(id);
        int[] o = new int[itemIds.size()];
        for (int i = 0; i < itemIds.size(); i++) {
            o[i] = itemIds.get(i);
        }
        CheckGroupVO checkGroupVO = new CheckGroupVO();
        checkGroupVO.setCheckItemIds(o);
        return checkGroupVO;
    }

    @Override
    public boolean saveUpdate(CheckGroupDTO checkGroupDTO) {
        return false;
    }
}
