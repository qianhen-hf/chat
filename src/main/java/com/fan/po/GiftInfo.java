package com.fan.po;

import java.util.Date;

public class GiftInfo {
    private Long giftId;

    private String giftName;

    private Long vcorn;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Long photoInfoId;

    private Long photoInfo3xId;

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName == null ? null : giftName.trim();
    }

    public Long getVcorn() {
        return vcorn;
    }

    public void setVcorn(Long vcorn) {
        this.vcorn = vcorn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPhotoInfoId() {
        return photoInfoId;
    }

    public void setPhotoInfoId(Long photoInfoId) {
        this.photoInfoId = photoInfoId;
    }

    public Long getPhotoInfo3xId() {
        return photoInfo3xId;
    }

    public void setPhotoInfo3xId(Long photoInfo3xId) {
        this.photoInfo3xId = photoInfo3xId;
    }
}