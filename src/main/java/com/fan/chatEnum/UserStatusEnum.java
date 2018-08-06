package com.fan.chatEnum;

public enum UserStatusEnum {

    ONLINE(1, "在线"),
    OFFLINE(2, "下线"),
    BUSY(3, "繁忙");

    private int code;
    private String message;

    /**
     * 构造一个枚举对象
     *
     * @param code
     * @param message
     */
    private UserStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     * @param code
     * @return UserStatusEnum
     */
    public static UserStatusEnum getByCode(int code) {
        for (UserStatusEnum _enum : values()) {
            if (_enum.getCode() == code) {
                return _enum;
            }
        }
        return null;
    }

}
