package com.jtrips.tao.api.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public class UserLoginRequest extends BaseRequest {
	private String username;
	private String password;
}
