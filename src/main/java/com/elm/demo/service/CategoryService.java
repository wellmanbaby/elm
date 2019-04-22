package com.elm.demo.service;

import com.elm.demo.dataobject.ProductCategory;


import java.util.List;

/**
 * Created by weiyao on 2019/4/17.
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
