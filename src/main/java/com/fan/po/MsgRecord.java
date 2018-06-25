package com.fan.po;

import java.util.Date;

public class MsgRecord {
    private Long msgId;

    private Long userId;

    private Long anchorId;

    private Long senderId;

    private Byte senderType;

    private Long receiverId;

    private Byte receiverType;

    private String msgCnt;

    private Byte status;

    private Date sendTime;

    private Date receiveTime;

    private Date createTime;

    private Date updateTime;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(Long anchorId) {
        this.anchorId = anchorId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Byte getSenderType() {
        return senderType;
    }

    public void setSenderType(Byte senderType) {
        this.senderType = senderType;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Byte getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(Byte receiverType) {
        this.receiverType = receiverType;
    }

    public String getMsgCnt() {
        return msgCnt;
    }

    public void setMsgCnt(String msgCnt) {
        this.msgCnt = msgCnt == null ? null : msgCnt.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
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
}