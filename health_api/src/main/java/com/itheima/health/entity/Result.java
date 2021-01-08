package com.itheima.health.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回结果
 */
@Data
public class Result implements Serializable{
    private boolean flag = true;//执行结果，true为执行成功 false为执行失败
    private String message;//返回结果信息
    private Object data;//返回数据
    private int status;  //  前端 提示状态码

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
