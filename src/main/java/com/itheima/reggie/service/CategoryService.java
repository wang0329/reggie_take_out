package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Category;

/**
 * @author Java不良人
 * @date 2023/3/28
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
