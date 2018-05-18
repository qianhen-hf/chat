package com.fan.Exception;

public class VRabbitUserErrors {

    public static final ErrorCode USER_ERROR = new ErrorCode(ErrorCode.BUSINESS_ERROR, 11, 110001, "用户名密码错误");
    public static final ErrorCode OBJECT_MAPPER_ERROR = new ErrorCode(ErrorCode.BUSINESS_ERROR, 11, 110001, "转换错误");
}
