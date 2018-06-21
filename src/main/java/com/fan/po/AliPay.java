package com.fan.po;

import lombok.Data;

@Data
public class AliPay {
    private Long id;

    private String outTradeNo;

    private String tradeNo;

    private String refundStatus;

    private String subject;

    private String notifyTime;

    private String notifyType;

    private String notifyId;

    private String signType;

    private String sign;

    private String paymentType;

    private String tradeStatus;

    private String gmtCreate;

    private String gmtPayment;

    private String gmtClose;

    private String sellerEmail;

    private String buyerEmail;

    private String sellerId;

    private String buyerId;

    private String price;

    private String totalFee;

    private String quantity;

    private String body;

    private String discount;

    private String isTotalFeeAdjust;

    private String useCoupon;

    private String extraCommonParam;

    private String businessScene;

    private String passbackParams;

}