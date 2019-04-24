package com.elm.demo.enums;

import lombok.Getter;

/*
* 订单状态的枚举类
* 记录订单的三种状态
* */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"已完成订单"),
    CANCEL(2,"订单取消"),
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
