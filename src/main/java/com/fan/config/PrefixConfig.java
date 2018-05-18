package com.fan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/5/17 15:55
 * @UpdateUser: hf
 * @UpdateDate: 2018/5/17 15:55
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "prefix")
@Component
public class PrefixConfig {
    private String userIdPrefix;
    private Long userOverTime;
}
