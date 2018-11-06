package com.fan.po;

public class IndexPic {
    private Long id;

    private Long photoId;

    private String describeDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getDescribeDesc() {
        return describeDesc;
    }

    public void setDescribeDesc(String describeDesc) {
        this.describeDesc = describeDesc == null ? null : describeDesc.trim();
    }
}