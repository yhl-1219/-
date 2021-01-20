package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangweili
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 根据起始月份以及推移数推算月份
     *
     * @param beginTime 起始时间
     * @param endTime   结束时间
     * @return example:<"2020-8",4>
     */
    @SelectProvider(type = GetMemberCountSelectProvider.class, method = "memberCountSelectProvider")
    ArrayList<HashMap<String, Object>> getMemberCount(@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("type") Integer type);

    /**
     * 根据分类计订单总数
     *
     * @return 订单名、数量的List集合
     */
    @Select("select count(t_order.SETMEAL_ID) as value, t_setmeal.name\n" +
            "from t_setmeal,\n" +
            "     t_order\n" +
            "where t_setmeal.ID = t_order.SETMEAL_ID\n" +
            "group by t_setmeal.name;")
    List<Map<String, String>> getSetmealCount();
}
