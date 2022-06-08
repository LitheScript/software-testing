package com.example.softwaretest.Result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {


    /**
    * 接口调用成功
    */
    SUCCESS(true,"00000","成功"),
    /**
    * 用户端错误
    */
    CLIENT_ERROR(false,"A0001","用户端错误"),
    /**
    * 服务端错误
    */
    SERVER_ERROR(false,"B0001","服务端错误");


    /**
    * 表示成功与否
    */
    private Boolean success;
    /**
    * 错误码
    */
    private String code;
    /**
    * 错误信息
    */
    private String message;
}
