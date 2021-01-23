package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.OrderSetting;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author wangweili
 */
public interface OrderSettingMapper extends BaseMapper<OrderSetting> {

    @Select("select * from t_ordersetting where orderdate between #{beginTime} and #{endTime}")
    List<OrderSetting> findSettingDataByYearAndMonth(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    @Update("update t_ordersetting set number = #{number} where orderdate = #{orderdate}")
    boolean updateNumberByOrderDate(@Param("number") int number, @Param("orderdate") String orderdate);

    @Update("update t_ordersetting set reservations = reservations + 1 where orderdate = #{orderdate}")
    void updateReservationsByOrderDate(@Param("orderdate") String orderdate);

    @Delete("delete from t_ordersetting where ORDERDATE<NOW()")
    void clearOrderSettingHistoryData();

}
