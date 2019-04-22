package com.elm.demo.dao;

import com.elm.demo.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by weiyao on 2019/4/18.
 * 买家商品信息接口
 */
/*JpaRepository<ProductInfo,String> String是主键类型
* 通过商品状态查询商品信息
* */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
