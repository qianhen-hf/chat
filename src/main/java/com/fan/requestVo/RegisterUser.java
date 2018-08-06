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
@ApiModel(description = "注册")
public class RegisterUser {

    @ApiModelProperty(value = "注册名称", name = "nickname")
    private String nickname;

    @ApiModelProperty(value = "注册账号", name = "chargeAmount")
    private String username;

    @ApiModelProperty(value="注册验证码",name="msgCode")
    private Integer msgCode;
}
