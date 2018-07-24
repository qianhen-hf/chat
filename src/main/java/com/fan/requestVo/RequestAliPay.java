package com.fan.requestVo;

import lombok.Data;

import java.util.Date;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/24 15:47
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/24 15:47
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Data
public class RequestAliPay {
    private Long id;
    private Date notify_time;
    private String notify_type;
    private String notify_id;
    private String app_id;
    private String charset;
    private String version;
    private String sign_type;
    private String sign;
    private String trade_no;
    private String out_trade_no;
    private String out_biz_no;
    private String buyer_id;
    private String buyer_logon_id;
    private String seller_id;
    private String seller_email;
    private String trade_status;
    private Double total_amount;
    private Double receipt_amount;
    private Double invoice_amount;
    private Double buyer_pay_amount;
    private Double point_amount;
    private Double refund_fee;
    private String subject;
    private String body;
    private Date gmt_create;
    private Date gmt_payment;
    private Date gmt_refund;
    private Date gmt_close;
    private String fund_bill_list;
    private String passback_params;
    private String voucher_detail_list;
}
