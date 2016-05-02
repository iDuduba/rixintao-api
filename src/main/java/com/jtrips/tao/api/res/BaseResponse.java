package com.jtrips.tao.api.res;

import com.jtrips.tao.api.enums.LangEnum;
import com.jtrips.tao.api.enums.CodeEnum;

import lombok.Data;

@Data
public abstract class BaseResponse {
	protected String respCode;
	protected String respMsg;
	
	public void setResponse(CodeEnum resp) {
		respCode = resp.getCode();
		respMsg = resp.getMsg();
	}
	
	public abstract void translate(LangEnum lang);
}
