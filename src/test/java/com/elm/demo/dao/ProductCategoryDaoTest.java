package com.elm.demo.dao;

import com.elm.demo.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    /*测试保存数据功能*/
    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        /*productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(4);*/
        productCategoryDao.save(productCategory);
    }
    /*测试查询功能*/
    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryDao.findById(1).get();
        System.out.println(productCategory.toString());
    }
    /*测试更新功能*/
    @Test
    public void changeOneTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        productCategory.setCategoryName("傻子最爱");
        productCategory.setCategoryType(13);
        productCategoryDao.save(productCategory);
    }
    /*测试全部查找功能*/
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(3,4,13);

        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(list);
        /*log.info("[查询类目列表] result=={}",result);*/
        log.info("[查询类目列表] result=={}",result);
        Assert.assertNotEquals(0,result.size());
    }
}