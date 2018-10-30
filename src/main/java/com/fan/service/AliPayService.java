package com.fan.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fan.config.AliPayConfig;
import com.fan.mapper.ChargeInfoMapper;
import com.fan.po.ChargeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

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
@Slf4j
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
        model.setBody("重庆夜色充值");
        model.setSubject("V兔充值支付");
        model.setOutTradeNo(chargeInfo.getBusiId().toString());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(String.valueOf(chargeInfo.getChargeAmount() / 100.0));
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


    AtomicLong atomicLong = new AtomicLong(30000001);
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");

    public String aliPay() {
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getUrl(), aliPayConfig.getAppId(), aliPayConfig.getRsaPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("重庆夜色充值");
        model.setSubject("V兔充值支付");
        String formatDate = dateFormatter.format(LocalDate.now());
        model.setOutTradeNo(formatDate + atomicLong.getAndIncrement() + "");
        model.setTimeoutExpress("30m");
        model.setTotalAmount(String.valueOf(1 / 100.0));
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


    public String aliWebPay() {
        // 商户订单号，商户网站订单系统中唯一订单号，必填
//        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
//        // 订单名称，必填
//        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
//        System.out.println(subject);
//        // 付款金额，必填
//        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
//        // 商品描述，可空
//        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");
//        // 超时时间 可空
//        String timeout_express = "2m";
//        // 销售产品码 必填
//        String product_code = "QUICK_WAP_WAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(aliPayConfig.getUrl(), aliPayConfig.getAppId(), aliPayConfig.getRsaPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
        AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        String formatDate = dateFormatter.format(LocalDate.now());
        String formatTime = dateFormatter.format(LocalDate.now());
        model.setOutTradeNo(formatDate + formatTime + atomicLong.getAndIncrement() + "");
        model.setSubject("充值支付");
        model.setTotalAmount(String.valueOf(1 / 100.0));
        model.setBody("黄帆充值");
        model.setTimeoutExpress("30m");
        model.setProductCode("QUICK_WAP_WAY");
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        // 设置同步地址
//        alipay_request.setReturnUrl(AlipayConfig.return_url);
        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipay_request).getBody();
            System.out.println(form);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }
}
