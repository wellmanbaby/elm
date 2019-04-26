package com.elm.demo.service.impl;

import com.elm.demo.converter.OrderMaster2OrderDTOConverter;
import com.elm.demo.dao.OrderDetailDao;
import com.elm.demo.dao.OrderMasterDao;
import com.elm.demo.dataobject.OrderDetail;
import com.elm.demo.dataobject.OrderMaster;
import com.elm.demo.dataobject.ProductInfo;
import com.elm.demo.dto.CartDTO;
import com.elm.demo.dto.OrderDTO;
import com.elm.demo.enums.OrderStatusEnum;
import com.elm.demo.enums.PayStatusEnum;
import com.elm.demo.enums.ResultEnum;
import com.elm.demo.exception.SellException;
import com.elm.demo.service.OrderService;
import com.elm.demo.service.ProductService;
import com.elm.demo.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    public OrderServiceImpl() {
        super();
    }

    /*创建订单
    * 1.订单入库，订单详情入库
    * 2.减库存
    * */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //订单Id声明
        String orderId = KeyUtil.genUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品（数量：不能让前端来传数量，要根据数据库中的数量；价格：不能由前端来定，要根据数据库价格）
        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            //订单id应该在一开始就生成了
            orderDetail.setOrderId(orderId);

            //将productInfo里的商品属性拷贝到orderDetail中
            BeanUtils.copyProperties(productInfo,orderDetail);

            orderDetailDao.save(orderDetail);

        }



        //3.写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        //BeanUtils.copyProperties(orderDTO,orderMaster);这个会覆盖orderId和OrderAmount，所有前移
        orderMasterDao.save(orderMaster);

        //4.扣库存
        //会出现多线程超买的现象，要是两个线程同时访问查库存，库存都是1，都会购买，出错，redis锁
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    /*查询单个订单*/
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findByOrderId(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
           throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /*查询订单列表*/
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());

    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
