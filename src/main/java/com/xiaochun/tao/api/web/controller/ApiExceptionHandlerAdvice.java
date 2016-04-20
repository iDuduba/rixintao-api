package com.xiaochun.tao.api.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.google.common.base.Throwables;
import com.xiaochun.tao.api.res.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandlerAdvice {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public CommonResponse exception(Exception exception, WebRequest request) {		
		CommonResponse error = new CommonResponse();
		error.setRespCode("9999");
		error.setRespMsg(Throwables.getRootCause(exception).getMessage());
		return error;
	}
	
}
