package com.fan.service;

import com.fan.util.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/5/17 16:44
 * @UpdateUser: hf
 * @UpdateDate: 2018/5/17 16:44
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class SendMsgService {
    @Autowired
    RestTemplate restTemplate;

    public Boolean sendMessage(String tel, String message) {
        try {
            String action = "send"; //表示发送短信
            String account = "922108"; //用户名
            String password = "2EEGSc"; //密码
            String mobile = "******"; //合法的手机号码，号码间用英文逗号分隔
            int code = (int) ((Math.random() * 9 + 1) * 100000);
            String content = "登录验证码为：" + code + "。【夜色】";
            content = SendMessage.urlEncode(content, "UTF-8");//	采用GBK 进行URLENCODE
            String extno = "10690108"; //为通道扩展子号码，可以为空
            String rt = "json";
            String parm = "action=" + action + "&account=" + account + "&password=" + password + "&mobile=" + tel + "&content=" + content + "&extno=" + extno + "&rt=" + rt;
            String host = "http://47.100.188.160:7862/sms"; //提交地址ַ
            String s = SendMessage.sendGet(host, parm);
            System.out.println(s + "--------------------------------");
            System.out.println(host + "?" + parm);

//            Map<String, String> map = new HashMap<>();
//            map.put("action","send");
//            map.put("account","922108");
//            map.put("password","2EEGSc");
//            map.put("mobile",tel);
//            map.put("content",content);
//            map.put("extno","10690108");
//            map.put("rt","json");
//            String forObject = restTemplate.getForObject(host, String.class, map);
//            System.out.println(forObject + "--------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
