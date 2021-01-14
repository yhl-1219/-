package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper extends BaseMapper<Order> {

    @Select("select count(1) from t_order where setmeal_id = #{setmealId} and member_id = #{memberId} and orderdate = #{orderDate}")
    int findOrderBySetmealIdAndOrderDateAndMemberId(@Param("setmealId") Integer setmealId, @Param("orderDate") String orderDate, @Param("memberId") Integer memberId);

}
