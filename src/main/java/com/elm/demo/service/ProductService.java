package com.elm.demo.service;

import com.elm.demo.dataobject.ProductInfo;
import com.elm.demo.dto.CartDTO;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiyao on 2019/4/19.
 */

public interface ProductService {

    ProductInfo findOne(String productId);

    /*查询所有在架商品*/
    List<ProductInfo> findUpAll();

    /*后台查询所有商品
    * 需要进行分页
    * */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}