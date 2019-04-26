package com.elm.demo.dto;

import lombok.Data;

/*
* 购物车
* */
@Data
public class CartDTO {

    /*前端提交的商品id*/
    private String productId;

    /*前端提交的商品数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
