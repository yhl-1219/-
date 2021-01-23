package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Order;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author wangweili 
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select count(1) from t_order where setmeal_id = #{setmealId} and member_id = #{memberId} and orderdate = #{orderDate}")
    int findOrderBySetmealIdAndOrderDateAndMemberId(@Param("setmealId") Integer setmealId, @Param("orderDate") String orderDate, @Param("memberId") Integer memberId);

    @Select("select * from t_setmeal where id = #{id}")
    Setmeal getSetmealById(@Param("id") Integer id);

    @Select("select ispay from t_order where id=#{oid}")
    int findOrderStateById(@Param("oid") Integer oid);

    @Update("update t_ordersetting,t_order set t_ordersetting.reservations = t_ordersetting.reservations - 1 where t_order.id = #{id} and t_order.ORDERDATE=t_ordersetting.ORDERDATE")
    void updateReservationsByOrderDate(@Param("id") Integer id);

}
