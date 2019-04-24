package com.elm.demo.dataobject;

import com.elm.demo.enums.OrderStatusEnum;
import com.elm.demo.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/*
*买家订单管理
* */
@Entity
@Table(name = "cdn_om_zj")
@Data
@DynamicUpdate
public class OrderMaster {
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /*支付状态，默认0为未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

}
