package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

public interface MemberMapper extends BaseMapper<Member> {
    
    @Select("select concat(max(year(rpt_date.fdate)), '-', rpt_date.fmonth) as name, count(*) as member\n" +
            "from rpt_date,\n" +
            "     t_member\n" +
            "where rpt_date.fdate = t_member.REGTIME\n" +
            "and rpt_date.fyear = year(date_add(#{nowDate},interval +${month} month))\n" +
            "and rpt_date.fmonth = month(date_add(#{nowDate},interval +${month} month))\n" +
            "group by rpt_date.fmonth;")
    HashMap<String,Object> getMemberCount(@Param("month") Integer month, @Param("nowDate") String nowDate);
}
