package com.fan.controller;

import com.aliyun.oss.OSS;
import com.fan.config.SmsConfig;
import com.fan.po.User;
import com.fan.service.external.OssService;
import com.fan.service.redis.RedisOperator;
import com.fan.util.HttpRequestHelper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Autowired
    OssService ossService;


    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("getSing")
    public String  getSing(User user){
        System.out.println(smsConfig.getSignature());
        return  smsConfig.getSignature();
    }

    @PostMapping("test")
    public String  getTest(HttpServletRequest httpServletRequest){
        redisOperator.leftPush("list","bbbbbb");
        redisOperator.setList("list",0,"aaaaa");
        String list = redisOperator.leftPop("list");
//        System.out.println(a);
//        a = httpServletRequest.getParameter("a");
//        System.out.println(a);
        System.out.println(list);
        System.out.println("____________________");
        String bodyString = HttpRequestHelper.getBodyString(httpServletRequest);
        System.out.println(bodyString);
        return list;
    }

    @RequestMapping("test1")
    public String  getTest1(HttpServletRequest httpServletRequest){
        String url = ossService.getObjectUrl("2_2x.png");
        return url;
    }
}
