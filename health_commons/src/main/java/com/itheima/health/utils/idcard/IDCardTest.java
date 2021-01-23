package com.itheima.health.utils.idcard;

import org.junit.Test;

public class IDCardTest {
    static  String cid = "411330199606063413";
            /**
             * 方法名：testParseGender
             * 详述：根据身份证号求性别
             * @return 说明返回值含义
             * @throw 说明发生此异常的条件
             */
            @Test
            public void testParseGender() {
                String gender = IDCardsUtils.parseGender(cid);
                System.out.println("性别：\t" + gender);
            }

            /**
             * 方法名：testCheckCardId
             * 详述：判断身份证号是否通过校验

             * @return 说明返回值含义
             * @throw 说明发生此异常的条件
             */

            @Test
            public void testCheckCardId() {
                boolean flag = IDCardsUtils.checkCardId(cid);
                System.out.println("身份证号是否通过校验：\t" + flag);
            }

            /**
             * 方法名：testParseAge
             * 详述：通过身份证号求得年龄
             * 修改记录+版本号：修改者，修改描述（一句话）
             * @return 说明返回值含义
             * @throw 说明发生此异常的条件
             */

            @Test
            public void testParseAge() {
                int age = IDCardsUtils.parseAge(cid);
                System.out.println("年龄：\t" + age);
            }

            /**
             * 方法名：testParseAddress
             * 详述：通过身份证号求出生地
             * @return 说明返回值含义
             * @throw 说明发生此异常的条件
             */

            @Test
            public void testParseAddress() {
                String address = IDCardsUtils.parseAddress(cid);
                System.out.println("出生地：\t" + address);
            }

            /**
             * 方法名：testParseBirthday
             * 详述：根据身份证号求出生日期
             * @return 说明返回值含义
             * @throw 说明发生此异常的条件
             */
            @Test
            public void testParseBirthday(){
                String birthday = IDCardsUtils.parseBirthday(cid);
                System.out.println("出生日期是：\t" + birthday);
            }

        }



