package com.fan.chatEnum;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/7/23 9:32
 * @UpdateUser: hf
 * @UpdateDate: 2018/7/23 9:32
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public enum OrderPayType {

    ALI_PAY(1, "支付宝app"),
    WEI_XIN_PAY(2, "微信app");


    private String orderDesc;
    private Integer value;

    OrderPayType(Integer value, String orderDesc) {
        this.value = value;
        this.orderDesc = orderDesc;
    }

    public Integer getValue() {
        return value;
    }
}
