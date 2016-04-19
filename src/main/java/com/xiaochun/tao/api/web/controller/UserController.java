package com.xiaochun.tao.api.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.xiaochun.tao.api.enums.RespCodeEnum;
import com.xiaochun.tao.api.exception.KeywordNotFoundException;
import com.xiaochun.tao.api.req.UserLoginRequest;
import com.xiaochun.tao.api.res.CommonResponse;
import com.xiaochun.tao.api.res.UserLoginResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
	
	/* @PathVariable("userId") int user
	 * @RequestParam(value = "data", required = true) UserLoginRequest request
	 * @RequestBody UserLoginRequest request
	 */
	@RequestMapping(value = "/login")	
//	public UserLoginResponse login(@RequestBody UserLoginRequest request) {		
	public UserLoginResponse login(@RequestParam(value="language",required=false,defaultValue="JP") String lang, @RequestParam(value="data", required=true) String data) {		
		
		UserLoginRequest request = JSONObject.parseObject(data, UserLoginRequest.class);
		log.debug("Login : {}", request);

		if(!Strings.isNullOrEmpty(lang) && lang.compareTo("JP") == 0) {
		  throw new KeywordNotFoundException(lang);
		}
		
		UserLoginResponse response = new UserLoginResponse();	
		
		response.setResponse(RespCodeEnum.SUCCESS);
		response.setUserId(UUID.randomUUID().toString());
		return response;
	}
	
	@ExceptionHandler(KeywordNotFoundException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public CommonResponse myError(HttpServletRequest request, Exception exception) {
	  CommonResponse error = new CommonResponse();
        error.setRespCode(""+HttpStatus.BAD_REQUEST.value());
        error.setRespMsg(exception.getLocalizedMessage());
//        error.setUrl(request.getRequestURL().append("/error/111").toString());
        return error;
    }
}
