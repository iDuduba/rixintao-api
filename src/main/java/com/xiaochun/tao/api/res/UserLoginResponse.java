package com.xiaochun.tao.api.res;

import lombok.Data;

@Data
public class UserLoginResponse extends BaseResponse {
	private String userId;

	@Override
	public void translate(LangEnum lang) {
	}
}
