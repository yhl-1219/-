package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.dto.SetmealDTO;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.mapper.SetmealMapper;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.aliyunoss.AliyunUtils;
import com.itheima.health.utils.redis.RedisUtil;
import com.itheima.health.utils.resources.RedisConstant;
import com.itheima.health.vo.SetmealVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author wangweili
 */
@Service
@Transactional
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Override
    public boolean saveUpdate(SetmealDTO setmealDTO) {
        saveOrUpdate(setmealDTO);
        baseMapper.deleteSetmealAndCheckGroupById(setmealDTO.getId());
        Integer[] checkgroupIds = setmealDTO.getCheckgroupIds();
        if (checkgroupIds != null && checkgroupIds.length != 0) {
            for (Integer checkgroupId : checkgroupIds) {
                baseMapper.addSetmealAndCheckGroupById(setmealDTO.getId(), checkgroupId);
            }
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        baseMapper.deleteSetmealAndCheckGroupById(id);
        Setmeal setmeal = new Setmeal();
        setmeal.setId(id);
        setmeal.setIs_delete(1);
        updateById(setmeal);
        return true;
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        String queryString = queryPageBean.getQueryString();
        QueryWrapper<Setmeal> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        if (queryString != null && queryString.length() != 0) {
            wrapper.like("name", queryString);
        }
        Page<Setmeal> page = page(new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize()), wrapper);
        return new PageResult(page.getTotal(), page.getRecords());
    }

    @Override
    public int[] findGroupIdsBySetmealId(Integer id) {
        List<Integer> groupIds = baseMapper.findGroupIdsBySetmealId(id);
        int[] o = new int[groupIds.size()];
        for (int i = 0; i < groupIds.size(); i++) {
            o[i] = groupIds.get(i);
        }
        return o;
    }

    @Override
    public void clearOssImg() {
        Set<String> members = RedisUtil.getMembersOfSet(RedisConstant.ALL_SETMEAL_PIC_SET);
        if (members.size() != 0) {
            for (String uuidFilename : members) {
                if (!RedisUtil.existsKey(RedisConstant.SINGLE_PIC + uuidFilename)) {
                    AliyunUtils.deleteFile(uuidFilename);
                    System.out.println("------阿里云删除垃圾图片------");
                    RedisUtil.removeSetMember(RedisConstant.ALL_SETMEAL_PIC_SET, uuidFilename);
                }
            }
        }
    }

    @Override
    public SetmealVO findSetMealDetailById(int id) {
        return null;
    }
}
