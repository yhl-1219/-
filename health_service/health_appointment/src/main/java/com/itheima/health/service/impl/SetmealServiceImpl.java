package com.itheima.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.health.dto.SetmealDTO;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.mapper.SetmealMapper;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.aliyunoss.AliyunUtils;
import com.itheima.health.utils.redis.RandomRedisExpiredTime;
import com.itheima.health.utils.redis.RedisUtil;
import com.itheima.health.utils.resources.RedisConstant;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wangweili
 */
@Service
@Transactional
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Override
    public boolean saveUpdate(SetmealDTO setmealDTO) {
        Integer id = setmealDTO.getId();
        if (id != null) {
            baseMapper.deleteSetmealAndCheckGroupById(id);
            RedisUtil.delete(RedisConstant.SETMEAL_FINDALL + id);
            RedisUtil.delete(RedisConstant.SETMEAL_FINDALL);
        }
        saveOrUpdate(setmealDTO);
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
        setmeal.setIsDelete(1);
        updateById(setmeal);
        return true;
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        String queryString = queryPageBean.getQueryString();
        QueryWrapper<Setmeal> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        if (queryString != null && queryString.length() != 0) {
            wrapper.like("name", queryString).or().like("helpcode", queryString).or().like("code", queryString);
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
    public Setmeal findSetMealDetailById(int id) {
        if (!RedisUtil.existsKey((RedisConstant.SETMEAL_FINDALL + id))) {
            Setmeal setmeal = baseMapper.selectById(id);
            List<CheckGroup> checkGroupList = baseMapper.findCheckGroupIdsBySetmealId(id);
            for (CheckGroup checkGroup : checkGroupList) {
                List<CheckItem> checkItemList = baseMapper.findCheckItemsByGroupId(checkGroup.getId());
                checkGroup.setCheckItems(checkItemList);
            }
            setmeal.setCheckGroups(checkGroupList);
            RedisUtil.set(RedisConstant.SETMEAL_FINDALL + id, setmeal, RandomRedisExpiredTime.getExpireTime(), TimeUnit.MINUTES);
            return setmeal;
        } else {
            return RedisUtil.get(RedisConstant.SETMEAL_FINDALL + id);
        }
    }

    @Override
    @SneakyThrows
    public List<Setmeal> findAll() {
        List<Setmeal> findAll = RedisUtil.get(RedisConstant.SETMEAL_FINDALL);
        if (findAll == null) {
            findAll = list();
            RedisUtil.set(RedisConstant.SETMEAL_FINDALL, findAll, RandomRedisExpiredTime.getExpireTime(), TimeUnit.MINUTES);
        }
        return findAll;
    }

}
