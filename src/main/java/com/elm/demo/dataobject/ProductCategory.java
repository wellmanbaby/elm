package com.elm.demo.dataobject;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by weiyao on 2019/4/17.
 * 类目
 */
@Entity
@Table(name = "cdn_proc_zj")

public class ProductCategory extends InfoMixin implements Serializable {
    /*类目id*/
    @Id
    @GeneratedValue
    private Integer categoryId;
    @Column
    private String categoryName;
    @Column
    private Integer categoryType;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {

    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }
}
