package com.fan.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
public class UserLoginVo {

    @ApiModelProperty(value="登陆用户名",name="userName")
    private String userName;

    @ApiModelProperty(value="登陆验证码",name="msgCode")
    private Integer msgCode;

    @ApiModelProperty(value="系统类型1安卓2ios",name="osType")
    private Byte osType;

    @ApiModelProperty(value="系统版本",name="osVersion")
    private String osVersion;

    @ApiModelProperty(value="手机型号",name="mobileModel")
    private String mobileModel;

    @ApiModelProperty(value="app版本",name="appVersion")
    private String appVersion;

    @ApiModelProperty(value="app名",name="appName")
    private String appName;

}