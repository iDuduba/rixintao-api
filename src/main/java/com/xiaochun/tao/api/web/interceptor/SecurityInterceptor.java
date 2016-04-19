package com.xiaochun.tao.api.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.xiaochun.tao.api.enums.RespCodeEnum;
import com.xiaochun.tao.api.res.BaseResponse;
import com.xiaochun.tao.api.res.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Value("${api.secretKey}")
	private String secretKey;

	@Value("${api.skipSign:false}")
	private boolean skipSign;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (!skipSign) {
			String appid = request.getParameter("appid");
			String sign = request.getParameter("sign");
			String salt = request.getParameter("salt");
			String data = request.getParameter("data");

			StringBuilder md5String = new StringBuilder();
			md5String.append(appid).append(data).append(salt).append(secretKey);
			String md5 = DigestUtils.md5DigestAsHex(md5String.toString().getBytes());

			if (!Objects.equal(md5, sign)) {
				CommonResponse comResponse = new CommonResponse();
				comResponse.setRespCode(RespCodeEnum.MD5_ERROR.getCode());
				comResponse.setRespMsg(RespCodeEnum.MD5_ERROR.getMsg());

				log.error("签名错误->Client:{},Server:{}", sign, md5);

				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write(JSONObject.toJSONString(comResponse));
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}
}
