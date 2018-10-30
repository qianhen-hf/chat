package com.fan.controller;

import com.fan.chatEnum.OrderPayType;
import com.fan.po.ChargeInfo;
import com.fan.requestVo.RequestCharge;
import com.fan.service.AliPayService;
import com.fan.service.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.ws.rs.POST;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/19 16:41
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/19 16:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Api(value = "支付", tags = {"支付接口"})
@RequestMapping("pay")
@RestController
public class RechargeController {
    @Autowired
    RechargeService rechargeService;


    @ApiOperation(value = "充值")
    @PostMapping("recharge")
    public String recharge(RequestCharge requestCharge) {
        return rechargeService.recharge(requestCharge);
    }

    @ApiIgnore
    @GetMapping("recharge1")
    public String recharge1() {
        RequestCharge requestCharge = new RequestCharge();
        requestCharge.setChargeAmount(1l);
        requestCharge.setType(1);
        requestCharge.setUserId(1l);
        return rechargeService.recharge(requestCharge);
    }


    @ApiIgnore
    @RequestMapping("recharge2")
    public String recharge2() {
        return rechargeService.recharge();
    }

    @ApiIgnore
    @GetMapping("recharge3")
    public String recharge3() {
        return rechargeService.rechargeWeb();
    }
}
