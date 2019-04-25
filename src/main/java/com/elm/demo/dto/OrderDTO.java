package com.elm.demo.dto;

import com.elm.demo.dataobject.OrderDetail;
import com.elm.demo.enums.OrderStatusEnum;
import com.elm.demo.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
* 数据传输对象，在各个层传输数据
* 使用DTO类来继承entity实体类，在DTO类里放一些业务字段，并提供get、set方法。
* 当我们在业务逻辑层或者交互层用到一些数据库中不存在的字段时，我们就需要在DTO类里放这些字段，
* 这些字段的意义就相当于一些经处理过的数据库字段，实质意义就是方便数据交互，提高效率。
* */
@Data
public class OrderDTO {
    /*订单id*/
    private String orderId;

    /*买家姓名*/
    private String buyerName;

    /*买家电话*/
    private String buyerPhone;

    /*买家地址*/
    private String buyerAddress;

    /*买家微信的openid*/
    private String buyerOpenid;

    /*订单金额*/
    private BigDecimal orderAmount;

    /*订单状态,默认0为新下单*/
    private Integer orderStatus;

    /*支付状态，默认0为未支付*/
    private Integer payStatus;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
