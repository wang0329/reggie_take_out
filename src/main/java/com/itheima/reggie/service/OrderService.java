package com.itheima.reggie.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Orders;
/**
 * @author Java不良人
 * @date 2023/3/29
 */
public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);
}
