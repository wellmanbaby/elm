package com.elm.demo.exception;

import com.elm.demo.enums.ResultEnum;

/*
* 捕获异常
* */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();

    }

    public SellException(Integer code,String message){
        super(message);

        this.code = code;

    }

}
