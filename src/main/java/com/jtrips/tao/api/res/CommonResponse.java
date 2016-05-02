package com.jtrips.tao.api.res;

import com.jtrips.tao.api.enums.LangEnum;
import com.jtrips.tao.api.enums.CodeEnum;

import lombok.Data;

@Data
public class CommonResponse extends BaseResponse {

	public CommonResponse() {
		this(CodeEnum.SUCCESS);
	}
	
	public CommonResponse(CodeEnum resp) {
		respCode = resp.getCode();
		respMsg = resp.getMsg();		
	}
	
	@Override
	public void translate(LangEnum lang) {
	}
}
