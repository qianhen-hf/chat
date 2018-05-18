package com.fan.Exception;

public class VRabbitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ErrorCode errorcode;

	private String extraInfo;

	private static String format = "errorcode:%d,typeid:%d,appid:%d,code:%d,errormsg:%s \n extrainfo:%s";

	private static String traceFormat = "errorcode:%d,typeid:%d,appid:%d,code:%d,errormsg:%s \n extrainfo:%s \n%s";

	public VRabbitException(ErrorCode errorcode) {
		this.errorcode = errorcode;
	}

	public VRabbitException(ErrorCode errorcode, String extraInfo) {
		super(formatMsg(errorcode, extraInfo));
		this.errorcode = errorcode;
		this.extraInfo = extraInfo;
	}

	public VRabbitException(ErrorCode errorcode, String extraInfo, String traceformat) {
		super(formatMsg(errorcode, extraInfo, traceformat));
		this.errorcode = errorcode;
		this.extraInfo = extraInfo;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public ErrorCode getErrorcode() {
		return errorcode;
	}

	private static String formatMsg(ErrorCode errorcode, String extraInfo) {

		return String.format(format, errorcode.getErrorCode(), errorcode.getTypeId(), errorcode.getAppId(), errorcode.getCode(), errorcode.getDesc(), extraInfo);
	}

	private static String formatMsg(ErrorCode errorcode, String extraInfo, String trace) {

		return String.format(traceFormat, errorcode.getErrorCode(), errorcode.getTypeId(), errorcode.getAppId(), errorcode.getCode(), errorcode.getDesc(), extraInfo, trace);
	}

}
