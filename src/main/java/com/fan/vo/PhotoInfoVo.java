package com.fan.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/8/6 11:36
 * @UpdateUser: hf
 * @UpdateDate: 2018/8/6 11:36
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Data
public class PhotoInfoVo implements Serializable{
    private Long id;

    private Long userId;

    private String photoUrl;

    private Integer seq;

    private Date createTime;

    private Date updateTime;
}
