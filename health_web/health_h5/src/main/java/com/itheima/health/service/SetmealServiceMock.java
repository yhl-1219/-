package com.itheima.health.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.dto.SetmealDTO;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.utils.redis.RedisUtil;
import com.itheima.health.utils.resources.MessageConstant;
import com.itheima.health.utils.resources.RedisConstant;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/21 10:49 下午
 */
public class SetmealServiceMock implements SetmealService {

    @Override
    public boolean saveUpdate(SetmealDTO setmealDTO) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public boolean delete(Integer id) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public int[] findGroupIdsBySetmealId(Integer id) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public void clearOssImg() {
    }

    @Override
    public Setmeal findSetMealDetailById(int id) {
        Setmeal o = RedisUtil.get(RedisConstant.SETMEAL_FINDALL + id);
        if (o == null) {
            throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
        } else {
            return o;
        }
    }

    @Override
    public List<Setmeal> findAll() {
        List<Setmeal> o = RedisUtil.get(RedisConstant.SETMEAL_FINDALL);
        if (o == null) {
            throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
        } else {
            return o;
        }
    }

    @Override
    public boolean saveBatch(Collection<Setmeal> entityList, int batchSize) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Setmeal> entityList, int batchSize) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public boolean updateBatchById(Collection<Setmeal> entityList, int batchSize) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public boolean saveOrUpdate(Setmeal entity) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public Setmeal getOne(Wrapper<Setmeal> queryWrapper, boolean throwEx) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Setmeal> queryWrapper) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public <V> V getObj(Wrapper<Setmeal> queryWrapper, Function<? super Object, V> mapper) {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }

    @Override
    public BaseMapper<Setmeal> getBaseMapper() {
        throw new RuntimeException(MessageConstant.APPOINTMENT_MOCK_THROW_EXCEPTION_WORD);
    }
}
