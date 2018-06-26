package com.fan.vo;

import lombok.Data;

@Data
public class UserLoginVo {

    private Byte osType;

    private String osVersion;

    private String mobileModel;

    private String appVersion;

    private String appname;

}