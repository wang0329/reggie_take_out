package com.itheima.reggie.common;

/**
 * @author Java不良人
 * @date 2023/3/28
 *
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}