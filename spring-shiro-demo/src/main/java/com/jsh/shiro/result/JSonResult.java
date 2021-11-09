package com.jsh.shiro.result;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JSonResult<T> {
    private int code;
    private String msg;
    private boolean success;
    private T data;

    public static <T> JSonResult success(T data) {
        JSonResult jSonResult = new JSonResult();
        jSonResult.setSuccess(true);
        jSonResult.setData(data);
        return jSonResult;
    }

    public static <T> JSonResult error(String msg){
        JSonResult jsonResult = new JSonResult();
        jsonResult.setSuccess(false);
        jsonResult.setMsg(msg);
        return jsonResult;
    }

}
