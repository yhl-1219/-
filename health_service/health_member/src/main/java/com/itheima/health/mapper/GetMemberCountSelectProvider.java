package com.itheima.health.mapper;

/**
 * @author wangweili
 * @version 1.0
 * @date 2021/1/20 11:01 上午
 */
public class GetMemberCountSelectProvider {

    /**
     * 根据起止时间及筛选模式返回不同的数据
     *
     * @param beginTime 起始时间
     * @param endTime   截止时间
     * @param type      类型
     * @return 结果集
     */
    public String memberCountSelectProvider(String beginTime, String endTime, Integer type) {
        String sql1 = "";
        String sql3 = "";
        String sql2 = " as name, count(*) as member\n" +
                "from rpt_date,\n" +
                "     t_member\n" +
                "where rpt_date.fdate = t_member.REGTIME\n" +
                " and rpt_date.fdate > '" + beginTime +
                "' and rpt_date.fdate < '" + endTime +
                "' group by rpt_date.";
        switch (type) {
            //年
            case 0:
                sql1 = "select rpt_date.fyear as name, count(*) ";
                sql3 = "fyear order by max(fdate);";
                break;
            //月
            case 1:
                sql1 = "select concat(max(year(rpt_date.fdate)), '-', rpt_date.fmonth) ";
                sql3 = "fmonth order by max(fdate);";
                break;
            //周
            case 2:
                sql1 = "select concat(max(year(rpt_date.fdate)),'-',rpt_date.fweek,'周') ";
                sql3 = "fweek order by max(fdate);";
                break;
            //日
            case 3:
                sql1 = "select rpt_date.fdate as name, count(*) ";
                sql3 = "fdate order by max(fdate);";
                break;
            default:
                throw new RuntimeException();
        }
        return sql1 + sql2 + sql3;
    }
}
