package com.fan.service;

import com.fan.mapper.ConsumeInfoMapper;
import com.fan.po.*;
import com.fan.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/10/9 15:46
 * @UpdateUser: hf
 * @UpdateDate: 2018/10/9 15:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class ConsumeInfoService {
    @Autowired
    ConsumeInfoMapper consumeInfoMapper;
    @Autowired
    UserService userService;
    @Autowired
    GiftInfoService giftInfoService;


    @Transactional
    public boolean reward(Long userId, Long anchorId, Long giftId) {
        User user = userService.selectUserByUserId(userId);
        GiftInfo giftInfo = giftInfoService.selectGiftById(giftId);
        if (user.getAmount() < giftInfo.getVcorn()) {
            return false;
        }
        ConsumeInfo consumeInfo = new ConsumeInfo();
        consumeInfo.setUserId(userId);
        consumeInfo.setAnchor(anchorId);
        consumeInfo.setGiftId(giftId);
        consumeInfoMapper.insertSelective(consumeInfo);
        User upUser = new User();
        upUser.setUserId(userId);
        upUser.setAmount(user.getAmount() - giftInfo.getVcorn());
        userService.updateUser(upUser);
        return true;
    }

    public List<ConsumeInfo> selectConsumeInfoByAnchorId(Long anchorId) {
        ConsumeInfoExample consumeInfoExample = new ConsumeInfoExample();
        ConsumeInfoExample.Criteria criteria = consumeInfoExample.createCriteria();
        criteria.andAnchorEqualTo(anchorId);
        return consumeInfoMapper.selectByExample(consumeInfoExample);
    }

    public List<ConsumeInfo> selectConsumeInfoByUserId(Long userId) {
        ConsumeInfoExample consumeInfoExample = new ConsumeInfoExample();
        ConsumeInfoExample.Criteria criteria = consumeInfoExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return consumeInfoMapper.selectByExample(consumeInfoExample);
    }
}
