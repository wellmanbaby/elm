package com.elm.demo.service.impl;

import com.elm.demo.dataobject.ProductInfo;
import com.elm.demo.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;//不在ProductServiceImpl.java中添加@service注解的话会报错

    @Test
    public void findOne() throws Exception {

        ProductInfo productInfo = productService.findOne("12345");
        Assert.assertEquals("12345",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() throws Exception {
        //page参数代表第0页，size参数代表第0页有多少内容
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("油条");
        productInfo.setProductPrice(new BigDecimal(1));
        productInfo.setProductStock(0);
        productInfo.setProductIcon("http://xxxxxx.com");
        productInfo.setProductDescription("十三香油条");
        productInfo.setProductStatus(ProductStatusEnum.Down.getCode());
        productInfo.setCategoryType(13);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

}