package com.elm.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/*
*跟前端直接交互的类，ProductVO是包含类目以及类目下商品的一个商品类
* 中间层
* */
@Data
public class ProductVO {

    /*
    * 前端需求文档里类目名和商品名都是用的name变量
    * 为了区分辨识，我们用JsonProperty去标识
    * */
    @JsonProperty("name")
    private String catagoryName;

    @JsonProperty("type")
    private String catagoryType;

    /*
    * 这是最内层对象，是一个集合，需要再写一个集合类
    * 给前端进行展示的类跟我们后台的类其实是不一样的，这是完全根据前端需求写的类
    * */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
