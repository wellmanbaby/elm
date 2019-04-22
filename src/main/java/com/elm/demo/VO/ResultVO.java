package com.elm.demo.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by weiyao on 2019/4/19.
 */
@Data
public class ResultVO<T> {

    /*错误码，0的时候表示成功*/
    private Integer code;

    /*提示成功，根据错误码*/
    private String msg;

    private T data;
}
