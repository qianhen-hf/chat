package com.fan.controller;

import com.fan.Exception.ChatException;
import com.fan.po.User;
import com.fan.service.CustomerService;
import com.fan.vo.ResponseResult;
import com.fan.vo.UserVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @ApiOperation(value = "更新用户/主播基本资料")
    @PostMapping("/updateUser")
    public ResponseResult updateUser(UserVo userVo) {
        logger.info("开始处理更新用户信息,参数：", userVo);
        ResponseResult result;
        try {
            customerService.updateUser(userVo);
            result = new ResponseResult();
        } catch (Exception e) {
            logger.error("更新用户发生异常", e);
            result = new ResponseResult(false);
        }
        logger.info("结束更新用户信息");
        return result;
    }


    @ApiOperation(value = "获取主播基本资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "anchorId", value = "主播ID", required = true, dataType = "java.lang.Long")
    })
    @PostMapping("/getUserById")
    public ResponseResult getUserById(Long anchorId) {
        ResponseResult responseResult = new ResponseResult();
        User user = customerService.selectUserByUserId(anchorId);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        responseResult.setData(userVo);
        return responseResult;
    }


    @ApiOperation(value = "用户关注主播")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "java.lang.Long"),
            @ApiImplicitParam(name = "anchorId", value = "主播ID", required = true, dataType = "java.lang.Long")
    })
    @PostMapping("/addFocus")
    public ResponseResult addFocus(long userId, long anchorId) {
        logger.info("开始处理关注,userId={},anchorId={}", userId, anchorId);
        ResponseResult result;
        try {
            customerService.addFocus(userId, anchorId);
            result = new ResponseResult();
        } catch (Exception e) {
            logger.error("处理关注发生异常", e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理关注");
        return result;
    }

    @ApiOperation(value = "查询关注/粉丝列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "java.lang.Long")
    })
    @GetMapping("/findFocus")
    public ResponseResult findFocus(long userId) {
        logger.info("开始查询关注/粉丝列表,userId={}", userId);
        ResponseResult result;
        try {
            List<User> users = customerService.findFocus(userId);
            result = new ResponseResult();
            result.setData(users);
        } catch (Exception e) {
            logger.error("查询关注/粉丝列表异常", e);
            result = new ResponseResult(false);
        }
        logger.info("结束查询关注/粉丝列表");
        return result;
    }

    @ApiOperation(value = "申请提现到支付宝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "主播ID", required = true, dataType = "java.lang.Long"),
            @ApiImplicitParam(name = "accountName", value = "账户名", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "account", value = "支付宝账号", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "money", value = "提现金额", required = true, dataType = "java.lang.Long"),
    })
    @PostMapping("/applyDeposite")
    public ResponseResult applyDeposite(Long userId, String accountName, String account, long money) {
        logger.info("开始处理提现申请，参数：userId={},money={}", new String[]{userId.toString(), String.valueOf(money)});
        ResponseResult result;
        try {
            customerService.applyWithdraw(userId, accountName, account, money);
            result = new ResponseResult();
        } catch (ChatException chatExp) {
            logger.error("提现申请处理失败", chatExp);
            result = new ResponseResult(ResponseResult.FAIL, chatExp.getMessage(), null);
        } catch (Exception e) {
            logger.error("提现申请处理异常", e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理提现申请");
        return result;
    }

    @ApiOperation(value = "投诉用户/主播")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accuserId", value = "投诉人ID", required = true, dataType = "java.lang.Long"),
            @ApiImplicitParam(name = "accuserdId", value = "被投诉人Id", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "content", value = "投诉内容", required = true, dataType = "java.lang.String")
    })
    @PostMapping("/advise")
    public ResponseResult advise(long accuserId, long accuserdId, String content) {
        logger.info("开始处理投诉,accuserId={},accuserdId={}", accuserId, accuserdId);
        ResponseResult result;
        try {
            customerService.addAdviseInfo(accuserId, accuserdId, content);
            result = new ResponseResult();
        } catch (Exception e) {
            logger.error("处理投诉发生异常", e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理投诉");
        return result;
    }

    @ApiOperation(value = "更新主播在线状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "主播Id", required = true, dataType = "java.lang.Long"),
            @ApiImplicitParam(name = "status", value = "在线状态 1：在线 2：下线 3：繁忙", required = true, dataType = "java.lang.Integer")
    })
    @PostMapping("/updateStatus")
    public ResponseResult updateStatus(long userId, int status) {
        logger.info("开始处理主播在线状态,userId={},status={}", userId, status);
        ResponseResult result;
        try {
            customerService.updateStatus(userId, status);
            result = new ResponseResult();
        } catch (ChatException chatExp) {
            logger.error("处理在线状态失败", chatExp);
            result = new ResponseResult(ResponseResult.FAIL, chatExp.getMessage(), null);
        } catch (Exception e) {
            logger.error("处理在线状态发生异常", e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理主播在线状态");
        return result;
    }

    @ApiOperation(value = "直播记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true,dataType = "java.lang.Long"),
            @ApiImplicitParam(name = "anchorId",value = "主播Id",required = true,dataType = "java.lang.Long"),
            @ApiImplicitParam(name = "status",value = "状态 1：开始，2：结束",required = true,dataType = "java.lang.Integer")
    })
    @PostMapping("/startLive")
    public ResponseResult startLive(long userId,long anchorId,int status) {
        logger.info("开始处理直播,userId={},anchorId={}",userId,anchorId);
        ResponseResult result;
        try {
            customerService.recordLiveLog(userId,anchorId,status);
            result = new ResponseResult();
        }  catch(Exception e) {
            logger.error("处理直播发生异常",e);
            result = new ResponseResult(false);
        }
        logger.info("结束处理直播");
        return result;
    }

}
