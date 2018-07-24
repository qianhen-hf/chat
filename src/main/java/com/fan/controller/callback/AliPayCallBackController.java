package com.fan.controller.callback;

import com.fan.Exception.VRabbitException;
import com.fan.Exception.VRabbitUserErrors;
import com.fan.po.AliPay;
import com.fan.requestVo.RequestAliPay;
import com.fan.service.AliPayCallBackService;
import com.fan.util.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("aliPayCallBack")
    public String aliPayNotify(HttpServletRequest request, HttpServletResponse response, RequestAliPay requestAliPay) {
        /** 支付宝相关参数说明地址https://docs.open.alipay.com/204/105301/ ***/
        log.info("支付回调");
        try {
//            AliPay aliPay = ClassUtils.requestToBean(request, AliPay.class, true);
            AliPay aliPay = new AliPay();
            ClassUtils.lineBeanToHumpBean(requestAliPay, aliPay);
            Boolean flag = aliPayCallBackService.aliPayNotify(aliPay, request.getParameterMap());
            if (flag) {
                return "fail";
            }
        } catch (Exception e) {
            throw new VRabbitException(VRabbitUserErrors.PAY_CALL_BACK_ERROR);
        }
        return "success";
    }

}
