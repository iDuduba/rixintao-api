package com.xiaochun.tao.api.res;

import com.xiaochun.tao.api.enums.LangEnum;

import lombok.Data;

@Data
public class CommonResponse extends BaseResponse {
	@Override
	public void translate(LangEnum lang) {
	}
}
