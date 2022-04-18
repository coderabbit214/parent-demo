package com.coderabbit.security.exception;

import com.coderabbit.security.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(UtilException.class)
    @ResponseBody
    public Result UtilExceptionhandler(UtilException e, HttpServletResponse resp){
        e.printStackTrace();
        return Result.error().msg(e.getMsg());
    }

}