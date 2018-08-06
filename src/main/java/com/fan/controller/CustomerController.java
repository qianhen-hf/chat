package com.fan.controller;

import com.fan.Exception.ChatException;
import com.fan.config.PrefixConfig;
import com.fan.po.User;
import com.fan.service.CustomerService;
import com.fan.service.SendMsgService;
import com.fan.vo.ResponseResult;
import com.fan.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping("/updateUser")
    public ResponseResult updateUser(UserVo userVo) {
        logger.info("开始处理更新用户信息,参数：",userVo);
        ResponseResult result;
        try {
            customerService.updateUser(userVo);
            result = new ResponseResult();
        } catch(Exception e) {
            logger.error("更新用户发生异常",e);
            result = new ResponseResult(false);
        }
        logger.info("结束更新用户信息");
        return result;
    }


    @RequestMapping("/addFocus")
    public ResponseResult addFocus(long userId,long anchorId) {
        logger.info("开始处理关注,userId={},anchorId={}",userId,anchorId);
        ResponseResult result;
        try {
            customerService.addFocus(userId,anchorId);
            result = new ResponseResult();
        } catch(Exception e) {
            logger.error("处理关注发生异常",e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理关注");
        return result;
    }

    @RequestMapping("/findFocus")
    public ResponseResult findFocus(long userId) {
        logger.info("开始查询关注/粉丝列表,userId={}",userId);
        ResponseResult result;
        try {
            List<User> users = customerService.findFocus(userId);
            result = new ResponseResult();
            result.setData(users);
        } catch(Exception e) {
            logger.error("查询关注/粉丝列表异常",e);
            result = new ResponseResult(false);
        }
        logger.info("结束查询关注/粉丝列表");
        return result;
    }

    @RequestMapping("/applyDeposite")
    public ResponseResult applyDeposite(Long userId,String accountName,String account,long money) {
        logger.info("开始处理提现申请，参数：userId={},money={}",new String[]{userId.toString(),String.valueOf(money)});
        ResponseResult result;
        try{
            customerService.applyWithdraw(userId,accountName,account,money);
            result = new ResponseResult();
        }catch(ChatException chatExp) {
            logger.error("提现申请处理失败",chatExp);
            result = new ResponseResult(ResponseResult.FAIL,chatExp.getMessage(),null);
        } catch(Exception e) {
            logger.error("提现申请处理异常",e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理提现申请");
        return result;
    }

    /**
     * @param accuserId 投诉人Id
     * @param accuserdId 被投诉人Id
     * @return
     */
    @RequestMapping("/advise")
    public ResponseResult advise(long accuserId,long accuserdId,String content) {
        logger.info("开始处理投诉,accuserId={},accuserdId={}",accuserId,accuserdId);
        ResponseResult result;
        try {
            customerService.addAdviseInfo(accuserId,accuserdId,content);
            result = new ResponseResult();
        } catch(Exception e) {
            logger.error("处理投诉发生异常",e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理投诉");
        return result;
    }

    @RequestMapping("/updateStatus")
    public ResponseResult updateStatus(long userId,int status) {
        logger.info("开始处理主播在线状态,userId={},status={}",userId,status);
        ResponseResult result;
        try {
            customerService.updateStatus(userId,status);
            result = new ResponseResult();
        } catch(ChatException chatExp) {
            logger.error("处理在线状态失败",chatExp);
            result = new ResponseResult(ResponseResult.FAIL,chatExp.getMessage(),null);
        } catch(Exception e) {
            logger.error("处理在线状态发生异常",e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理主播在线状态");
        return result;
    }

}
