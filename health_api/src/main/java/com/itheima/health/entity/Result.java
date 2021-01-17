package com.itheima.health.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回结果
 * 
 * @author wangweili 
 */
@Data
public class Result implements Serializable{

    /**
     * 执行结果，true为执行成功 false为执行失败
     */
    private boolean flag = true;

    /**
     * 返回结果信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 前端 提示状态码
     */
    private int status;

    public Result() {
    }

	   public Result(Object obj) {
        this.data = obj;
    }

    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }


}
