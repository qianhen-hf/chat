package com.fan.Exception;

public class ErrorCode {

  // 错误 0-31位表示应用的错误编码 32-56 位表示应用的编码 57-64位表示错误的类型
  private long errorCode;
  // 描述
  private String desc;

  public static final int NETWORK_ERROR = 1;

  public static final int DATABASE_ERROR = 2;

  public static final int BUSINESS_ERROR = 3;



  public ErrorCode(int typeId, int appId, int code, String desc) {

    this.errorCode = ((((long) typeId) & 0x0000000000ff) << 56)
        | ((((long) appId) & 0x0000000000ffffff) << 32) | ((((long) code) & 0x00000000ffffffff));
    this.desc = desc;
  }

  public int getTypeId() {
    return (int) ((errorCode >> 56) & 0xff);
  }


  public int getAppId() {
    return (int) ((errorCode >> 32) & 0xffffff);
  }


  public int getCode() {
    return (int) (errorCode & 0xffffffff);
  }

  public long getErrorCode() {
    return errorCode;
  }

  public String getDesc() {
    return desc;
  }


}
