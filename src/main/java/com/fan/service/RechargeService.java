package com.fan.service;

import com.fan.chatEnum.OrderPayType;
import com.fan.po.ChargeInfo;
import com.fan.requestVo.RequestCharge;
import com.fan.service.redis.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/23 9:40
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/23 9:40
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Service
@Slf4j
public class RechargeService {

    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");

    @Autowired
    AliPayService aliPayService;
    @Autowired
    RedisOperator redisOperator;
    @Value("${orderInitNum}")
    String orderInitNum;


    @Transactional
    public String recharge(RequestCharge requestCharge) {
        ChargeInfo chargeInfo = new ChargeInfo();
        chargeInfo.setChargeAmount(requestCharge.getChargeAmount());
        chargeInfo.setBusiId(createOrderNum("0101"));
        chargeInfo.setStatus(0);
        if (requestCharge.getType() == OrderPayType.ALI_PAY.getValue()) {
            return aliPayService.aliPay(chargeInfo);
        }
        return aliPayService.aliPay(chargeInfo);
    }

    public Long createOrderNum(String businessCode) {
        String formatDate = dateFormatter.format(LocalDate.now());
        if (!redisOperator.exists(formatDate)) {
            log.info("redis-key: {}", formatDate);
            redisOperator.setNx(formatDate, orderInitNum);
        }
        Long increment = redisOperator.increment(formatDate, 1);
        log.info("increment: {}", formatDate);
        StringBuilder append = new StringBuilder(formatDate).append(businessCode).append(increment);
        return Long.parseLong(append.toString());
    }

    @Transactional
    public String recharge() {
        return aliPayService.aliPay();
    }

    @Transactional
    public String rechargeWeb() {
        return aliPayService.aliWebPay();
    }
}
