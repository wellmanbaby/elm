package com.elm.demo.dao;

import com.elm.demo.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567");
        orderDetail.setOrderId("1111111");
        orderDetail.setProductId("12121212");
        orderDetail.setProductIcon("http://xxxxx.jpg");
        orderDetail.setProductName("熊掌");
        orderDetail.setProductPrice(new BigDecimal(222));
        orderDetail.setProductQuantity(2);
        OrderDetail result = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {

    }
}