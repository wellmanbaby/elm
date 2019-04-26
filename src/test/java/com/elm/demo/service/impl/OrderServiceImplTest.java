package com.elm.demo.service.impl;

import com.elm.demo.dataobject.OrderDetail;
import com.elm.demo.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";

    @Test
    public void create() throws Exception{
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小李");
        orderDTO.setBuyerAddress("怡红院");
        orderDTO.setBuyerPhone("18475849393");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail od1 = new OrderDetail();
        od1.setProductId("1234567");
        od1.setProductQuantity(3);

        OrderDetail od2 = new OrderDetail();
        od2.setProductId("123456");
        od2.setProductQuantity(1);

        orderDetailList.add(od1);
        orderDetailList.add(od2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);

        log.info("[创建订单] result={}", result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderService.findOne("1556241612577840507");
        log.info("[查询单个订单] result=={}",result);
        Assert.assertEquals("1556241612577840507",result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());

    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}