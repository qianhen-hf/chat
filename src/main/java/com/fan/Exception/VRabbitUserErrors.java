package com.fan.Exception;

public class VRabbitUserErrors {

    public static final ErrorCode USER_ERROR = new ErrorCode(ErrorCode.BUSINESS_ERROR, 11, 110001, "用户名或密码错误");
    public static final ErrorCode USER_MSG_CODE_ERROR = new ErrorCode(ErrorCode.BUSINESS_ERROR, 11, 110002, "用户名验证码错误");
    public static final ErrorCode ORDER_TYPE_ERROR = new ErrorCode(ErrorCode.BUSINESS_ERROR, 11, 110003, "未找到对应的类型");
    public static final ErrorCode PAY_CALL_BACK_ERROR = new ErrorCode(ErrorCode.BUSINESS_ERROR, 11, 110004, "支付回调错误");
    public static final ErrorCode OBJECT_MAPPER_ERROR = new ErrorCode(ErrorCode.BUSINESS_ERROR, 11, 110005, "转换错误");
    public static final ErrorCode USER_MSG_CODE_NOT_EXIST = new ErrorCode(ErrorCode.BUSINESS_ERROR, 11, 110006, "用户名验证码过期");
}
