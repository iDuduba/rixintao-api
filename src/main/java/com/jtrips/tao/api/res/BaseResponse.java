package com.jtrips.tao.api.res;

import com.jtrips.tao.api.enums.LangEnum;
import com.jtrips.tao.api.enums.RespCodeEnum;

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
