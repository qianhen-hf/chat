package com.fan.controller.callback;

import com.fan.Exception.VRabbitException;
import com.fan.Exception.VRabbitUserErrors;
import com.fan.po.AliPay;
import com.fan.service.AliPayCallBackService;
import com.fan.util.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/6/19 11:14
 * @UpdateUser: hf
 * @UpdateDate: 2018/6/19 11:14
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Slf4j
@ApiIgnore
@RestController
@RequestMapping("callback")
public class AliPayCallBackController {
    @Autowired
    AliPayCallBackService aliPayCallBackService;

    @RequestMapping("aliPayCallBack")
    public String aliPayNotify(HttpServletRequest request, HttpServletResponse response) {
        /** 支付宝相关参数说明地址https://docs.open.alipay.com/203/105286/ ***/
        log.info("支付回调");
        try {
            AliPay aliPay = ClassUtils.requestToBean(request, AliPay.class, true);
            Boolean flag = aliPayCallBackService.aliPayNotify(aliPay,request.getParameterMap());
            if(flag){
                return "fail";
            }
        }catch (Exception e){
            throw new VRabbitException(VRabbitUserErrors.PAY_CALL_BACK_ERROR);
        }
        return "success";
    }

}
