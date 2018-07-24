package com.fan.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/24 9:02
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/24 9:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Data
@ApiModel(description = "userId不用,会取token中的userid")
public class RequestUser {

    @ApiModelProperty(value = "支付金额", name = "chargeAmount")
    private String username;

    @ApiModelProperty(value="登陆验证码",name="msgCode")
    private Integer msgCode;
}
