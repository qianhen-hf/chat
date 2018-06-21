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
 * @CreateDate: 2018/6/21 17:16
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/21 17:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Data
@Configuration
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {
    private String accessKeyId;
    private String domain;
    private String accessKeySecret;
    private String product;
    private String regionId;
    private String signature;
}
