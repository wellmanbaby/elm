package com.elm.demo.controller;

import com.elm.demo.VO.ProductInfoVO;
import com.elm.demo.VO.ProductVO;
import com.elm.demo.VO.ResultVO;
import com.elm.demo.dataobject.ProductCategory;
import com.elm.demo.dataobject.ProductInfo;
import com.elm.demo.service.CategoryService;
import com.elm.demo.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品Controller
 * Created by weiyao on 2019/4/19.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询）
      /*  List<Integer> categoryTypeList = new ArrayList<>();
        //根据商品查询类目的传统方法
        for(ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简方法（java8, lambda）
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        //将查询到的类目存在productCategoryList集合中
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);


        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            //遍历类目，将类目名和类型值赋给中间层的值对象集合
            ProductVO productVO = new ProductVO();
            productVO.setCatagoryName(productCategory.getCategoryName());
            productVO.setCatagoryType(productCategory.getCategoryType());

            //上面的CatagoryName和setCatagoryType好搞，下面的productInfoVOList不好搞
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                //遍历productInfoList里的每一条productInfo，查它的CategoryType跟类目的CategoryType等不等
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                   /* productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setProductIcon(productInfo.getProductIcon());*/
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        //最外层
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");

        resultVO.setData(productVOList);

        //中间层
       /* ProductVO productVO = new ProductVO();
        resultVO.setData(Arrays.asList(productVO));

        //最内存
        ProductInfoVO productInfoVO = new ProductInfoVO();
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
*/
        return resultVO;

    }
}
