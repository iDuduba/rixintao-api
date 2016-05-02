package com.jtrips.tao.api.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.jtrips.tao.api.enums.LangEnum;
import com.jtrips.tao.api.enums.RespCodeEnum;
import com.jtrips.tao.api.exception.KeywordNotFoundException;
import com.jtrips.tao.api.req.UserLoginRequest;
import com.jtrips.tao.api.res.CommonResponse;
import com.jtrips.tao.api.res.HelloResponse;
import com.jtrips.tao.api.res.UserLoginResponse;
import com.jtrips.tao.service.HelloService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
	
  @Value("${api.version}")
  private String apiVersion;  

  @Value("${api.static.path}")
  private String staticPath;  

//  @Autowired
  @Reference(version = "1.0")
  private HelloService helloService;
  
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
		response.setSession(UUID.randomUUID().toString());
		response.translate(LangEnum.JP);
		return response;
	}
	
	DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
	
	@RequestMapping(value = "/hello")
	public HelloResponse hello(@RequestParam(value="name",required=false,defaultValue="tom") String name) {
      if(name.compareTo("yyy") == 0) {
        throw new KeywordNotFoundException(name);
      }	  
	  
      HelloResponse response = new HelloResponse();
      response.setResponse(RespCodeEnum.SUCCESS);
      response.add("serverApiVersion", apiVersion);
      response.add("scmRevision", "219");
      response.add("staticContextPath", staticPath);
      response.add("yes", helloService.sayHello(name));
      response.add("warnning", "不要骚扰我！");
      response.add("startTime", fmt.print(DateTime.now()));
      response.translate(LangEnum.CN);
	  return response;
	}
	
	/*
	@ExceptionHandler(KeywordNotFoundException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public CommonResponse myError(HttpServletRequest request, KeywordNotFoundException exception) {
	  CommonResponse error = new CommonResponse();
        error.setRespCode(""+HttpStatus.BAD_REQUEST.value());
        error.setRespMsg(exception.getLocalizedMessage());
        return error;
    }
    */
}
