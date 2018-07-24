package com.fan.service;

import com.fan.mapper.ChargeInfoMapper;
import com.fan.po.ChargeInfo;
import com.fan.po.ChargeInfoExample;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/23 9:25
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/23 9:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Service
public class ChargeInfoService {
    @Autowired
    ChargeInfoMapper chargeInfoMapper;


    public void insert(ChargeInfo chargeInfo) {
        chargeInfoMapper.insert(chargeInfo);
    }

    @Transactional
    public void changeStatus(Integer status, String tradeNo, Long chargeId) {
        ChargeInfoExample chargeInfoExample = new ChargeInfoExample();
        ChargeInfoExample.Criteria criteria = chargeInfoExample.createCriteria();
        criteria.andBusiIdEqualTo(chargeId);
        ChargeInfo chargeInfo = new ChargeInfo();
        chargeInfo.setStatus(status);
        chargeInfo.setTradeNo(tradeNo);
        chargeInfoMapper.updateByExampleSelective(chargeInfo, chargeInfoExample);
    }


    @Transactional
    public void charge(Integer status, String tradeNo, Long chargeId,Long amount) {
        ChargeInfoExample chargeInfoExample = new ChargeInfoExample();
        ChargeInfoExample.Criteria criteria = chargeInfoExample.createCriteria();
        criteria.andBusiIdEqualTo(chargeId);
        ChargeInfo chargeInfo = new ChargeInfo();
        chargeInfo.setStatus(status);
        chargeInfo.setTradeNo(tradeNo);
        chargeInfo.setChargeAmount(amount);
        chargeInfoMapper.updateByExampleSelective(chargeInfo, chargeInfoExample);
    }
}
