package com.itheima.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Java不良人
 * @date 2023/3/28
 *
 * 公共字段填充
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入自动填充------");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrent());
        metaObject.setValue("updateUser", BaseContext.getCurrent());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新自动填充------");
//        long id = Thread.currentThread().getId();
//        log.info("填充线程id为{}",id);
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrent());
    }
}
