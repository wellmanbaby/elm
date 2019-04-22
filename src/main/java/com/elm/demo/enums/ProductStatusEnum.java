package com.elm.demo.enums;

import lombok.Getter;

/**
 * Created by weiyao on 2019/4/19.
 */
@Getter
public enum ProductStatusEnum {
    Up(0,"在架"),
    Down(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
