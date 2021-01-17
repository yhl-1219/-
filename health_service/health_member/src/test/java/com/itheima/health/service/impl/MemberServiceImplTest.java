package com.itheima.health.service.impl;

import com.itheima.health.utils.date.DateUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class MemberServiceImplTest {

    @Test
    public void demo() {
        Date date = DateUtils.parseString2Date("2020-01", "yyyy-MM");
        System.out.println(date);
    }

}