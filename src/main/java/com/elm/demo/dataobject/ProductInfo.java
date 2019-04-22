package com.elm.demo.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 卖家端商品类
 * Created by weiyao on 2019/4/18.
 */
@Entity
@Table(name = "cdn_yao_zj")
@Data
public class ProductInfo {
    /*商品类Id不需要自增*/
    @Id
    private String productId;

    /*商品名*/
    @Column
    private String productName;

    /*商品价格*/
    @Column
    private BigDecimal productPrice;

    /*商品库存*/
    @Column
    private Integer productStock;

    /*商品描述*/
    @Column
    private String productDescription;

    /*商品小图*/
    @Column
    private String productIcon;

    /*s商品状态，0正常1下架*/
    @Column
    private Integer productStatus;

    /*类目编号*/
    @Column
    private Integer categoryType;

    public ProductInfo(String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }

    public ProductInfo() {

    }
}
