package com.elm.demo.dao;

import com.elm.demo.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by weiyao on 2019/4/17.
 * 操作类目表的接口
 *
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
   List<ProductCategory>  findByCategoryTypeIn(List<Integer> categoryTypeList);
}
