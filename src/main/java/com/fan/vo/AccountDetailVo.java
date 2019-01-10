package com.fan.vo;

import lombok.Data;

@Data
public class AccountDetailVo {
    private Long vcorn;

    private String occurTime;

    private String type;

    private String title;


    public long getAccountTotal() {
        return accountTotal;
    }

    public void setAccountTotal(long accountTotal) {
        this.accountTotal = accountTotal;
    }

    public long getAccountDay() {
        return accountDay;
    }

    public void setAccountDay(long accountDay) {
        this.accountDay = accountDay;
    }

    private long accountTotal;

    private long accountDay;

}
