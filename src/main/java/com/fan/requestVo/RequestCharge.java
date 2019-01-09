package com.fan.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/23 9:20
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/23 9:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Data
@ApiModel(description = "userId不用,会取token中的userid")
public class RequestCharge implements Serializable {
    @ApiModelProperty(value = "支付金额", name = "chargeAmount")
    private Long chargeAmount;
    @ApiModelProperty(value = "支付渠道", name = "type")
    private Integer type;
    @ApiModelProperty(value = "用户id", name = "userId")
    private Long userId;
    @ApiModelProperty(value = "充值套餐id", name = "rechargePackagreId")
    private Long rechargePackagreId;
}
