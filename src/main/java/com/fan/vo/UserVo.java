package com.fan.vo;

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
public class UserVo {
    private Long userId;

    private String name;

    private String token;

    private String nickName;

    private String autograph;

    private Integer sex;

    private String headPic;

    private Date createTime;

    private Date updateTime;

}
