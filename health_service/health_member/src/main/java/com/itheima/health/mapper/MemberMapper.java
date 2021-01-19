package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * @param month 月份偏移量
     * @param nowDate 起始月份
     * @return example:<"2020-8",4>
     */
    @Select("select concat(max(year(rpt_date.fdate)), '-', rpt_date.fmonth) as name, count(*) as member\n" +
            "from rpt_date,\n" +
            "     t_member\n" +
            "where rpt_date.fdate = t_member.REGTIME\n" +
            "and rpt_date.fyear = year(date_add(#{nowDate},interval +${month} month))\n" +
            "and rpt_date.fmonth = month(date_add(#{nowDate},interval +${month} month))\n" +
            "group by rpt_date.fmonth;")
    HashMap<String,Object> getMemberCount(@Param("month") Integer month, @Param("nowDate") String nowDate);

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
