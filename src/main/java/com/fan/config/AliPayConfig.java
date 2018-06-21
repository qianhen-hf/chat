package com.fan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/19 16:45
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/19 16:45
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "alipay")
@Data
@Component
public class AliPayConfig {
    private String appId;
    //私钥 pkcs8格式的
    private String rsaPrivateKey;
    //服务器异步通知页面路径
    private String notifyUrl;
    //请求网关地址
    private String  url;
    // 编码
    private String charset;
    // 返回格式
    private String format;
    // 支付宝公钥
    private String aliPayPublicKey;
    // RSA2
    private String signType;
}
