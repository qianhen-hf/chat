package com.fan.vo;

import lombok.Data;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/5/17 10:56
 * @UpdateUser: hf
 * @UpdateDate: 2018/5/17 10:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Data
public class WebResponse {
    private Object data;
    private String msg;
    private Integer code;
}
