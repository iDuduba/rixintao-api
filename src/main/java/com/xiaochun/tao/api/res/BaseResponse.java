package com.xiaochun.tao.api.res;

import com.xiaochun.tao.api.enums.LangEnum;
import com.xiaochun.tao.api.enums.RespCodeEnum;

import lombok.Data;

@Data
public abstract class BaseResponse {
	protected String respCode;
	protected String respMsg;
	
	public void setResponse(RespCodeEnum resp) {
		respCode = resp.getCode();
		respMsg = resp.getMsg();
	}
	
	public abstract void translate(LangEnum lang);
}
