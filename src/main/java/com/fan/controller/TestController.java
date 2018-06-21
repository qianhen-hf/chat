package com.fan.controller;

import com.fan.config.SmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/22 0:05
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/22 0:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    SmsConfig smsConfig;

    @RequestMapping("getSing")
    public String  getSing(){
        System.out.println(smsConfig.getSignature());
        return  smsConfig.getSignature();
    }
}
