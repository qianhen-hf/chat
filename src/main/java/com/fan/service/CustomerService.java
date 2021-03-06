package com.fan.service;

import com.fan.Exception.ChatException;
import com.fan.chatEnum.UserStatusEnum;
import com.fan.mapper.*;
import com.fan.po.*;
import com.fan.vo.AccountDetailVo;
import com.fan.vo.GiftDetailVo;
import com.fan.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: cr
 * @CreateDate: 2018/6/17 11:04
 * @UpdateUser: cr
 * @UpdateDate: 2018/6/17 11:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class CustomerService {

    @Autowired
    FocusInfoMapper focusInfoMapper;

    @Autowired
    DepositInfoMapper depositInfoMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdviseMapper adviseMapper;

    @Autowired
    LiveRecordMapper liveRecordMapper;

    @Autowired
    ConsumeInfoDao consumeInfoDao;

    @Autowired
    GiftInfoMapper giftInfoMapper;


    public void updateUser(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setUpdateTime(new Date());

        userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<User> selectUsersByUserIds(List<Long> userIds) {
        UserExample example = new UserExample();
        example.createCriteria().andUserIdIn(userIds).andUserTypeEqualTo(2);
        return userMapper.selectByExample(example);
    }

    public void addFocus(Long userId, Long anchorId) {
        FocusInfo focusInfo = new FocusInfo();
        focusInfo.setUserId(userId);
        focusInfo.setAnchorId(anchorId);
        Date nowTime = Calendar.getInstance().getTime();
        focusInfo.setFocusTime(nowTime);
        focusInfo.setCreateTime(nowTime);
        focusInfo.setUpdateTime(nowTime);
        focusInfoMapper.insert(focusInfo);
    }

    public List<User> findFocus(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        FocusInfoExample focusInfoExample = new FocusInfoExample();
        int userType = user.getUserType();
        //查询关注
        if (userType == 1) {
            focusInfoExample.createCriteria().andUserIdEqualTo(userId);
        }
        //查询粉丝
        else {
            focusInfoExample.createCriteria().andAnchorIdEqualTo(userId);
        }
        List<FocusInfo> focusInfos = focusInfoMapper.selectByExample(focusInfoExample);
        if (focusInfos == null || focusInfos.size() < 1) {
            return null;
        }
        List<Long> userIds = new ArrayList<>();
        for (FocusInfo focusInfo : focusInfos) {
            long tempId = userType == 1 ? focusInfo.getUserId() : focusInfo.getAnchorId();
            userIds.add(tempId);
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIn(userIds);
        return userMapper.selectByExample(userExample);
    }


    /**
     * 提现申请(支持支付宝账户)
     *
     * @param
     * @param
     * @param money
     * @return
     */
    public void applyWithdraw(Long userId, String accountName, String account, long money) throws ChatException {

        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getAmount().longValue() - money < 0) {
            throw new ChatException("可提现金额不足");
        }

        Date nowTime = Calendar.getInstance().getTime();
        DepositInfo depositInfo = new DepositInfo();
        depositInfo.setUserId(userId);
        depositInfo.setApplyAmount(money);
        depositInfo.setApplyTime(nowTime);
        depositInfo.setAccount(account);
        depositInfo.setAccountName(accountName);
        depositInfo.setStatus(1);
        depositInfo.setWay(1);
        depositInfo.setCreateTime(nowTime);
        depositInfo.setUpdateTime(nowTime);
        depositInfoMapper.insert(depositInfo);
    }

    public void addAdviseInfo(long accuserId, long accuserdId, String content) {
        User user = userMapper.selectByPrimaryKey(accuserId);
        int userType = user.getUserType();
        Advise advise = new Advise();
        advise.setAccuserId(accuserId);
        advise.setAccuserType(userType);
        int accUserdType = userType == 1 ? 2 : 1;
        advise.setAccuserdId(accuserdId);
        advise.setAccuserdType(accUserdType);
        advise.setContent(content);
        Date nowTime = Calendar.getInstance().getTime();
        advise.setAccTime(nowTime);
        advise.setCreateTime(nowTime);
        advise.setUdpateTime(nowTime);
        adviseMapper.insert(advise);
    }


    /**
     * 更新在线状态
     *
     * @param userId
     * @param status
     */
    public void updateStatus(long userId, int status) throws ChatException {
        UserStatusEnum userStatusEnum = UserStatusEnum.getByCode(status);
        if (null == userStatusEnum) {
            throw new ChatException("状态不支持");
        }
        User user = new User();
        user.setUserId(userId);
        user.setStatus(status);
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 直播记录
     *
     * @param userId
     * @param anchorId
     * @param status
     */
    public void recordLiveLog(long userId, long anchorId, int status) {
        LiveRecord liveRecord = new LiveRecord();
        liveRecord.setAnchor(anchorId);
        liveRecord.setUserId(userId);
        liveRecord.setStatus(status);
        Date now = new Date();
        liveRecord.setOccurTime(now);
        liveRecord.setCreateTime(now);
        liveRecord.setUpdateTime(now);

        liveRecordMapper.insert(liveRecord);
    }

    /**
     * 查询收到的礼物
     *
     * @param userId
     */
    public List<GiftDetailVo> findReceiveGift(long userId,int start,int end) {
        List<GiftDetailVo> list = consumeInfoDao.findGiftInfoByPage(userId,start,end);
        return list;
    }

//    public List<>

    /**
     * 查询收支明细
     * @param userId
     */
    public List<AccountDetailVo> findAccountDetail(long userId,int start,int end) {
        List<AccountDetailVo> list = consumeInfoDao.findAccountDetail(userId,start,end);
        return list;
    }


    /**
     * 查询收益
     * @param userId
     * @return
     */
    public AccountDetailVo findAccountTotal(long userId) {

        long accountTotal = consumeInfoDao.findAccountTotal(userId);
        long accountDay = consumeInfoDao.findAccountDay(userId);

        AccountDetailVo accountVo = new AccountDetailVo();
        accountVo.setAccountTotal(accountTotal);
        accountVo.setAccountDay(accountDay);

        return accountVo;
    }

}
