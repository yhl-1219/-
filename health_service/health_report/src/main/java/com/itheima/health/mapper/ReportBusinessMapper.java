package com.itheima.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.health.pojo.Member;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ReportBusinessMapper extends BaseMapper<Member> {

    //查询当日新增会员数量
    @Select("select count(1) from t_member where t_member.REGTIME = curdate()")
    int findTodayNewMember();
    //查询本周会员数量
    @Select("select count(1) from rpt_date,t_member,\n" +
            "\t\t(select rpt_date.fyear gyear,rpt_date.fweek gweek from rpt_date\n" +
            "\t\t\twhere rpt_date.fdate = curdate()) s\n" +
            "\twhere rpt_date.fdate = t_member.REGTIME \n" +
            "\tand rpt_date.fweek= s.gweek\n" +
            "\tand rpt_date.fyear= s.gyear")
    Integer findThisWeekNewMember();

    //查询本月新增会员数
    @Select("select count(1) from rpt_date rd,t_member tm ,\n" +
            "\t\t(select rd.fyear,rd.fmonth from rpt_date rd where rd.fdate = curdate()) m\n" +
            "\t\twhere rd.fdate = tm.REGTIME \n" +
            "\t\tand rd.fmonth = m.fmonth\n" +
            "\t\tand rd.fyear = m.fyear")
    Integer findThisMonthNewMember();
    //查询今日预约数
    @Select("select count(1) from t_order where ORDERDATE = curdate()")
    Integer findTodayOrderNumber();

    //查询今日到诊数
    @Select("select count(1) from t_order where ORDERDATE = curdate() and ORDERSTATUS='已到诊'")
    Integer findTodayVisitsNumber();

    //查询本周预约数
    @Select("select count(1)from t_order,rpt_date,\n" +
            "\t\t\t(select rd.fyear ,rd.fweek from rpt_date rd where rd.fdate=curdate()) m\n" +
            "\t\t\twhere t_order.ORDERDATE = rpt_date.fdate\n" +
            "\t\t\tand rpt_date.fweek = m.fweek\n" +
            "\t\t\tand rpt_date.fyear = m.fyear")
    Integer findThisWeekOrderNumber();

    //查询本周到诊数
    @Select("select count(1)from t_order,rpt_date,\n" +
            "\t\t\t(select rd.fyear ,rd.fweek from rpt_date rd where rd.fdate=curdate()) m\n" +
            "\t\t\twhere t_order.ORDERDATE = rpt_date.fdate\n" +
            "\t\t\tand rpt_date.fweek = m.fweek\n" +
            "\t\t\tand rpt_date.fyear = m.fyear\n" +
            "\t\t\tand t_order.ORDERSTATUS = '已到诊'")
    Integer findThisWeekVisitsNumber();

    //查询本月预约数
    @Select("select count(1)from t_order,rpt_date,\n" +
            "\t\t\t(select rd.fyear ,rd.fmonth from rpt_date rd where rd.fdate=curdate()) m\n" +
            "\t\t\twhere t_order.ORDERDATE = rpt_date.fdate\n" +
            "\t\t\tand rpt_date.fmonth = m.fmonth\n" +
            "\t\t\tand rpt_date.fyear = m.fyear")
    Integer findThisMonthOrderNumber();

    //查询本月到诊数
    @Select("select count(1)from t_order,rpt_date,\n" +
            "\t\t\t(select rd.fyear ,rd.fmonth from rpt_date rd where rd.fdate=curdate()) m\n" +
            "\t\t\twhere t_order.ORDERDATE = rpt_date.fdate\n" +
            "\t\t\tand rpt_date.fmonth = m.fmonth\n" +
            "\t\t\tand rpt_date.fyear = m.fyear\n" +
            "\t\t\tand t_order.ORDERSTATUS = '已到诊'")
    Integer findThisMonthVisitsNumber();

    //查询热门套餐
    @Select("select t_setmeal.NAME name, count(1) setmeal_count, concat(round(count(1)/max(s.total)*100,2),'%') proportion, t_setmeal.REMARK remark\n" +
            "from t_order,t_setmeal,\n" +
            "(select count(*) total from t_order) s\n" +
            "where t_order.SETMEAL_ID = t_setmeal.ID\n" +
            "group by t_order.SETMEAL_ID \n" +
            "order by count(1) desc LIMIT 0,4")
    List<Map> findHotSetmeal();

}
