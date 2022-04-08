package com.coderabbit.utildemo.result;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestResultExceptionHandler {

    /**
     * 自定义业务异常-BusinessException
     */
    @ResponseBody
    @ExceptionHandler(value = {BusinessException.class})
    public RestResult<?> handlerCustomException(BusinessException exception) {
        return RestResultUtils.failed(exception.getCode(), exception.getMsg());
    }

}
