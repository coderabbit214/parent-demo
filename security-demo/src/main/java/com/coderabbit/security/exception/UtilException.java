package com.coderabbit.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 自定义运行时异常
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UtilException extends RuntimeException{
    private String msg;
}