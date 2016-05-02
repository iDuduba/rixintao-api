package com.jtrips.tao.api.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

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
	public CommonResponse exception(Exception ex, WebRequest request) {	
	    log.error(Throwables.getRootCause(ex).getMessage());
		CommonResponse error = new CommonResponse();
		
      	  error.setResponse(RespCodeEnum.FAIL);
//      	  error.setRespMsg(Throwables.getRootCause(exception).getMessage());

		return error;
	}
	
	
	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    protected CommonResponse handleMaxUploadSizeExceededException(final HttpServletRequest request,
            final HttpServletResponse response, final Throwable e)
            throws IOException {
        log.warn(">> {}", e.getLocalizedMessage());
        CommonResponse error = new CommonResponse();
        error.setResponse(RespCodeEnum.UPLOAD_EXCEED);
        return error;
    }
 
    @ExceptionHandler(value = MultipartException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected CommonResponse handleGenericMultipartException(final HttpServletRequest request,
            final HttpServletResponse response, final Throwable e)
            throws IOException {
        Throwable rootCause = e;
        Throwable cause = e.getCause();
        while (cause != null && !cause.equals(rootCause)) {
            rootCause = cause;
            cause = cause.getCause();
        }
        CommonResponse error = new CommonResponse();
        error.setResponse(RespCodeEnum.UPLOAD_EERROR);
        error.setRespMsg(rootCause.getLocalizedMessage());
        return error;
    }
}
