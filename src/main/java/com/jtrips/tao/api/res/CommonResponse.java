package com.jtrips.tao.api.res;

import com.jtrips.tao.api.enums.LangEnum;
import com.jtrips.tao.api.enums.RespCodeEnum;

import lombok.Data;

@Data
public class CommonResponse extends BaseResponse {

	public CommonResponse() {
		this(RespCodeEnum.SUCCESS);
	}
	
	public CommonResponse(RespCodeEnum resp) {
		respCode = resp.getCode();
		respMsg = resp.getMsg();		
	}
	
	@Override
	public void translate(LangEnum lang) {
	}
}
