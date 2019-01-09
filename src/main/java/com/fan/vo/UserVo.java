package com.fan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.*;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/5/17 11:00
 * @UpdateUser: hf
 * @UpdateDate: 2018/5/17 11:00
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Data
@ApiModel(value="user对象",description="用户对象user")
public class UserVo {

    private Long userId;

    @ApiModelProperty(value="用户名",name="userName")
    private String userName;

    @ApiModelProperty(value="显示昵称",name="nickName")
    private String nickName;

    @ApiModelProperty(value="头像",name="photo")
    private String photo;

    @ApiModelProperty(value="注册时间",name="registerTime")
    private Date registerTime;

    @ApiModelProperty(value="余额",name="amount")
    private Long amount;

    @ApiModelProperty(value="星座",name="constellation")
    private String constellation;

    @ApiModelProperty(value="职业",name="occupation")
    private String occupation;

    private Integer isStop;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value="是否是主播",name="type")
    private Integer userType;

    @ApiModelProperty(value="主播状态",name="status")
    private Integer status;

    private String token;

    private List<HashMap<String,String>> rankList;

    public void setRankList(List<HashMap<String, String>> rankList) {
        this.rankList = rankList;
    }

}
