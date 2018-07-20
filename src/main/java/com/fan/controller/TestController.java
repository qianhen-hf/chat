package com.fan.controller;

import com.fan.config.SmsConfig;
import com.fan.service.redis.RedisOperator;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
@ApiIgnore
@RestController
@RequestMapping("vRabbit")
public class TestController {
    @Autowired
    SmsConfig smsConfig;
    @Autowired
    RedisOperator redisOperator;


    @GetMapping("getSing")
    public String  getSing(){
        System.out.println(smsConfig.getSignature());
        return  smsConfig.getSignature();
    }

    @GetMapping("test")
    public String  getTest(){
        redisOperator.leftPush("list","bbbbbb");
        redisOperator.setList("list",0,"aaaaa");
        String list = redisOperator.leftPop("list");
        System.out.println(list);
        return list;
    }
}
