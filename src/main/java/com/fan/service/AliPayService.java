package com.fan.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fan.config.AliPayConfig;
import com.fan.mapper.ChargeInfoMapper;
import com.fan.po.ChargeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/12 17:12
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/12 17:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class AliPayService {
    @Autowired
    AliPayConfig aliPayConfig;
    @Autowired
    ChargeInfoService chargeInfoService;


    public String aliPay(ChargeInfo chargeInfo) {
        chargeInfoService.insert(chargeInfo);

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getUrl(), aliPayConfig.getAppId(), aliPayConfig.getRsaPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("18050907021000000045");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            return response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
