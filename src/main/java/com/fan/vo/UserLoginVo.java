package com.fan.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UserLoginVo {

    @NotBlank(message = "osType不能为空")
    private Byte osType;

    private String osVersion;

    private String mobileModel;

    private String appVersion;

    private String appname;

}