package com.fan.po;

public class RechargePackage {
    private Long id;

    private Long amount;

    private String describeDesc;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescribeDesc() {
        return describeDesc;
    }

    public void setDescribeDesc(String describeDesc) {
        this.describeDesc = describeDesc == null ? null : describeDesc.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}