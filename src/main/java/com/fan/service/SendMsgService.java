package com.fan.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fan.config.SmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

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
@Slf4j
public class SendMsgService {
    @Autowired
    SmsConfig smsConfig;

    public Boolean sendMessage(String tel, String messageId, String content) {
        try {
            aliSend(tel, messageId, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean aliSend(String tel, String messageId, String content) {
        Boolean b = false;
        log.info("tel:{},content:{}", tel, content);
        try {
            IClientProfile profile = DefaultProfile.getProfile(smsConfig.getRegionId(), smsConfig.getAccessKeyId(), smsConfig.getAccessKeySecret());
            DefaultProfile.addEndpoint(smsConfig.getRegionId(), smsConfig.getRegionId(), smsConfig.getProduct(), smsConfig.getDomain());
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest sendSmsRequest = new SendSmsRequest();
            //使用post提交
            sendSmsRequest.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            sendSmsRequest.setPhoneNumbers(tel);
            //必填:短信签名-可在短信控制台中找到
            sendSmsRequest.setSignName(smsConfig.getSignature());
            //必填:短信模板-可在短信控制台中找到
            sendSmsRequest.setTemplateCode(messageId); //"SMS_137790152"
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            sendSmsRequest.setTemplateParam(content);  //{\"name\":\"wy\", \"code\":\"321545\"}
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            sendSmsRequest.setOutId(new StringBuilder().append(System.currentTimeMillis()).toString());
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                log.info("{}短信发送成功", sendSmsRequest.getPhoneNumbers());
            } else {
                System.out.println(sendSmsResponse.getCode());
            }
        } catch (Exception e) {
            log.error("发送短信错误，短信号码{},错误信息{}", tel, e);
            e.printStackTrace();
        }
        return b;
    }

}
