package com.coderabbit.security.result;

import lombok.Data;

/**
 * 统一返回结果
 */
@Data
public class Result<T> {
    private Boolean success;

    private Integer code;

    private String msg;

    private T data;

    //构造方法私有化
    private Result() {
    }

    //链式编程
    //成功静态方法
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMsg("成功");
        return r;
    }

    //失败静态方法
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR.getCode());
        r.setMsg("失败");
        return r;
    }

    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result msg(String message) {
        this.setMsg(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(T data) {
        this.setData(data);
        return this;
    }
}

