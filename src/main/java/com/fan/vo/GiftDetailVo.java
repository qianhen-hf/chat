package com.fan.vo;

import lombok.Data;

import java.util.Date;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/10/11 15:34
 * @UpdateUser: hf
 * @UpdateDate: 2018/10/11 15:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Data
public class GiftDetailVo {
    private Long giftId;

    private String giftName;

    private Long vcorn;

    private String photoUrl;

    private Long userId;

    private String nickName;

    private String receiveTime;

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }
}
