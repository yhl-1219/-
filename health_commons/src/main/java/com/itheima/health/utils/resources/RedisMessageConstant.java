package com.itheima.health.utils.resources;

/**
 * @author wangweili
 */
public class RedisMessageConstant {

    private RedisMessageConstant() {
    }

    /**
     * 用于缓存体检预约时发送的验证码
     */
    public static final String SENDTYPE_ORDER = "001";

    /**
     * 用于缓存手机号快速登录时发送的验证码
     */
    public static final String SENDTYPE_LOGIN = "002";

    /**
     * 用于缓存找回密码时发送的验证码
     */
    public static final String SENDTYPE_GETPWD = "003";

}
