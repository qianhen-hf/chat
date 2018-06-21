package com.fan.controller;

import com.fan.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("pay")
@RestController
public class AliPayController {
    @Autowired
    AliPayService aliPayService;

    @RequestMapping("aliPay")
    public String aliPay(){
        return aliPayService.aliPay();
    }
}
