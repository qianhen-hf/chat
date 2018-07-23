package com.fan.controller;

import com.fan.chatEnum.OrderPayType;
import com.fan.po.ChargeInfo;
import com.fan.requestVo.RequestCharge;
import com.fan.service.AliPayService;
import com.fan.service.RechargeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

@RequestMapping("vRabbit/pay")
@RestController
public class RechargeController {
    @Autowired
    RechargeService rechargeService;


    @PostMapping("recharge")
    public String recharge(@RequestBody RequestCharge requestCharge) {
        return rechargeService.recharge(requestCharge);
    }
}
