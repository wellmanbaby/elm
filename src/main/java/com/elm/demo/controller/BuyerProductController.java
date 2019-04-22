package com.elm.demo.controller;

import com.elm.demo.VO.ProductInfoVO;
import com.elm.demo.VO.ProductVO;
import com.elm.demo.VO.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 买家商品Controller
 * Created by weiyao on 2019/4/19.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @GetMapping("/list")
    public ResultVO list(){

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");

        ProductVO productVO = new ProductVO();
        resultVO.setData(Arrays.asList(productVO));

        ProductInfoVO productInfoVO = new ProductInfoVO();
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));

        return resultVO;

    }
}
