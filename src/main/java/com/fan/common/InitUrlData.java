package com.fan.common;

import java.util.HashMap;
import java.util.Map;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/1/23 9:07
 * @UpdateUser: hf
 * @UpdateDate: 2018/1/23 9:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class InitUrlData {

    public Map<String, Integer> getUrls() {
        Map<String, Integer> urls = new HashMap<>();
        urls.put("/vRabbit/login", 1);
        urls.put("/vRabbit/sendMsgCode", 1);
        return urls;
    }
}
