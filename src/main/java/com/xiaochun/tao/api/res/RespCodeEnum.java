package com.xiaochun.tao.api.res;

public enum RespCodeEnum {

	SUCCESS("0000", "成功"),	
	FAIL("9999","请求系统异常,请稍候再试."),
	MD5_ERROR("9998","请求参数非法.");
	
	private String code;
	private String msg;
	
	RespCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
