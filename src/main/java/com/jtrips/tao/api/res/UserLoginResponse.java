package com.jtrips.tao.api.res;

import lombok.Data;

//@JsonIgnoreProperties({ "session", "userId" })
@Data
public class UserLoginResponse extends I18nResponse {
	private String userId;
	
	//@JsonIgnore
	private String session;

}
