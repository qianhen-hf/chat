package com.fan.service;

import com.fan.mapper.GiftInfoMapper;
import com.fan.po.GiftInfo;
import com.fan.po.PhotoInfo;
import com.fan.util.ClassUtils;
import com.fan.vo.GiftInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/10/9 16:24
 * @UpdateUser: hf
 * @UpdateDate: 2018/10/9 16:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Service
public class GiftInfoService {
    @Autowired
    GiftInfoMapper giftInfoMapper;
    @Autowired
    PhotoInfoService photoInfoService;


    public GiftInfo selectGiftById(Long giftId) {
        return giftInfoMapper.selectByPrimaryKey(giftId);
    }

    public List<GiftInfoVo> getAllGift() {
        List<GiftInfo> giftInfos = giftInfoMapper.selectByExample(null);
//        photoInfoService.get
        List<GiftInfoVo> list = new ArrayList<>();
        for (GiftInfo giftInfo : giftInfos) {

            GiftInfoVo giftInfoVo = new GiftInfoVo();
            BeanUtils.copyProperties(giftInfo, giftInfoVo);
//            giftInfoVo.setGiftUrl();
            list.add(giftInfoVo);
        }
        return list;
    }
}
