package com.elm.demo.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/*
* 订单详情类
* */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;

    /*订单id*/
    @Column
    private String orderId;

    /*商品id*/
    @Column
    private String productId;

    /*商品名*/
    @Column
    private String productName;

    /*商品价格*/
    @Column
    private BigDecimal productPrice;

    /*商品数量*/
    @Column
    private Integer productQuantity;

    /*商品小图*/
    @Column
    private String productIcon;
}
