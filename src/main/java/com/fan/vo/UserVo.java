package com.fan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

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

    @ApiModelProperty(value="用户名",name="username")
    private String username;

    @ApiModelProperty(value="显示昵称",name="nickname")
    private String nickname;

    @ApiModelProperty(value="头像",name="photo")
    private String photo;

    @ApiModelProperty(value="注册时间",name="registerTime")
    private Date registerTime;

    private Long amount;

    private Byte isStop;

    private Date createTime;

    private Date updateTime;

    private Integer type;

    private String token;

}
