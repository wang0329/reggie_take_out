package com.itheima.reggie.common;

/**
 * @author Java不良人
 * @date 2023/3/28
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrent(){
        return threadLocal.get();
    }
}
