package com.elm.demo.dao;

import com.elm.demo.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String OPENID = "110";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("儿子");
        orderMaster.setBuyerAddress("垃圾场");
        orderMaster.setBuyerPhone("100101");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(22));

        OrderMaster result = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = PageRequest.of(0,1);
        //PageRequest里面会实现Pageable
        Page<OrderMaster> result = orderMasterDao.findByBuyerOpenid(OPENID, request);
        log.info("[根据OPENID查询单个订单] result=={}",result);
        Assert.assertNotEquals(0,result.getTotalElements());

        System.out.println(result.getTotalElements());
    }

    @Test
    public void findOne() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        OrderMaster result = orderMasterDao.findByOrderId("123456");
        log.info("[查询单个订单] result=={}",result);
        Assert.assertNotNull(result);
    }
}