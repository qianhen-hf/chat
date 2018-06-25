package com.fan.po;

import java.util.Date;

public class Advise {
    private Long id;

    private Long accuserId;

    private Integer accuserType;

    private Long accuserdId;

    private Integer accuserdType;

    private String content;

    private Date accTime;

    private Date createTime;

    private Date udpateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccuserId() {
        return accuserId;
    }

    public void setAccuserId(Long accuserId) {
        this.accuserId = accuserId;
    }

    public Integer getAccuserType() {
        return accuserType;
    }

    public void setAccuserType(Integer accuserType) {
        this.accuserType = accuserType;
    }

    public Long getAccuserdId() {
        return accuserdId;
    }

    public void setAccuserdId(Long accuserdId) {
        this.accuserdId = accuserdId;
    }

    public Integer getAccuserdType() {
        return accuserdType;
    }

    public void setAccuserdType(Integer accuserdType) {
        this.accuserdType = accuserdType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAccTime() {
        return accTime;
    }

    public void setAccTime(Date accTime) {
        this.accTime = accTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUdpateTime() {
        return udpateTime;
    }

    public void setUdpateTime(Date udpateTime) {
        this.udpateTime = udpateTime;
    }
}