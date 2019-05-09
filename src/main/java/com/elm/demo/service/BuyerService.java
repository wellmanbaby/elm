package com.elm.demo.service;

import com.elm.demo.dto.OrderDTO;

/*
* 验证买家身份必须做的一个Service
* */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);

}
