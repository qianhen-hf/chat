package com.fan.vo;

import lombok.Data;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: hf
 * @CreateDate: 2018/4/11 15:53
 * @UpdateUser: hf
 * @UpdateDate: 2018/4/11 15:53
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Data
public class ResponseResult {
    public static final Integer SUCCESS = 1;
    public static final Integer ERROR = 1;

    private String msg;
    private Object data;
    private int code;

    public ResponseResult() {

    }

    public ResponseResult(boolean flag) {
        this.code = flag ? 1 : 0;
        this.msg = flag ? "操作成功" : "操作失败";
    }

}