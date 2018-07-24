package com.fan.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fan.chatEnum.OrderPayType;
import com.fan.config.AliPayConfig;
import com.fan.mapper.AliPayMapper;
import com.fan.po.AliPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/20 12:56
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/20 12:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Slf4j
@Service
public class AliPayCallBackService {
    @Autowired
    AliPayConfig aliPayConfig;
    @Autowired
    AliPayMapper aliPayMapper;
    ChargeInfoService chargeInfoService;


    public Boolean aliPayNotify(AliPay aliPay, Map<String, String[]> parameterMap) {
        try {
            Boolean b = checkCallBack(parameterMap);
            if (b) {
                aliPayMapper.insert(aliPay);
                Double totalAmount = aliPay.getTotalAmount() * 100;
                chargeInfoService.charge(1, aliPay.getTradeNo(), Long.parseLong(aliPay.getOutTradeNo()), Long.parseLong(totalAmount.toString()));
                return true;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.info("回调验证失败");
            log.error("回调验证失败:{}", e.getErrMsg());
            return false;
        }
        return false;
    }

    private Boolean checkCallBack(Map requestParams) throws AlipayApiException {
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        return AlipaySignature.rsaCheckV1(params, aliPayConfig.getAliPayPublicKey(), aliPayConfig.getCharset(), aliPayConfig.getSignType());
    }
}



