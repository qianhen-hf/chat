package com.fan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@ApiModel
public class UserLoginVo {

    @ApiModelProperty(value="系统类型1安卓2ios",name="username")
    private Byte osType;

    @ApiModelProperty(value="系统版本",name="osVersion")
    private String osVersion;

    @ApiModelProperty(value="手机型号",name="mobileModel")
    private String mobileModel;

    @ApiModelProperty(value="app版本",name="appVersion")
    private String appVersion;

    @ApiModelProperty(value="app名",name="appname")
    private String appname;

}