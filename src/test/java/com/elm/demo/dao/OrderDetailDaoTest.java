package com.elm.demo.dao;

import com.elm.demo.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678");
        orderDetail.setOrderId("33333333");
        orderDetail.setProductId("1312123");
        orderDetail.setProductIcon("http://xxxxwwx.jpg");
        orderDetail.setProductName("鱼");
        orderDetail.setProductPrice(new BigDecimal(34));
        orderDetail.setProductQuantity(33);
        OrderDetail result = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId("33333333");
        System.out.println(orderDetailList);
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}