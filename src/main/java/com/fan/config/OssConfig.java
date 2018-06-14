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
 * @CreateDate: 2018/6/11 10:28
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/11 10:28
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Configuration
@ConfigurationProperties(prefix = "oss")
@Data
@Component
public class OssConfig {
    private String AccessKeyID;
    private String AccessKeySecret;
    private String RoleArn;
    private Long TokenExpireTime;
    private String Policy;

}
