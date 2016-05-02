package com.jtrips.tao.api.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.google.common.base.Throwables;
import com.jtrips.tao.api.enums.RespCodeEnum;
import com.jtrips.tao.api.res.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandlerAdvice {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public CommonResponse exception(Exception exception, WebRequest request) {	
	    log.error(Throwables.getRootCause(exception).getMessage());
		CommonResponse error = new CommonResponse();
      	  error.setResponse(RespCodeEnum.FAIL);
//      	  error.setRespMsg(Throwables.getRootCause(exception).getMessage());
		return error;
	}
	
}
