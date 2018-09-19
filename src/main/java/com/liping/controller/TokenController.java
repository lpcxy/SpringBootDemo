package com.liping.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liping.console.RequestResult;
import com.liping.domain.IamUser;
import com.liping.service.TokenInterface;
import com.liping.utils.CommonConstans;
import com.liping.utils.ErrorUtil;
import com.liping.utils.JsonUtil;

@RestController
@RequestMapping("v1")
public class TokenController
{
	@Autowired
	private TokenInterface tokenService;
	/**
	 * 获取token
	 * */
	@RequestMapping(value="/auth/token", produces = CommonConstans.APPLICATION_JSON,method = RequestMethod.POST)
	public String users(@RequestBody String body, HttpServletResponse httpResponse){
		RequestResult requestResult = new RequestResult();
		try{
			IamUser user = JsonUtil.parseObject(body, IamUser.class);
			String token = tokenService.getToken(user);
			httpResponse.setHeader(CommonConstans.TOKEN_HEADER, token);
		}catch(Exception e){
			ErrorUtil.handleException(requestResult, e);
		}
		String result = JsonUtil.toJson(requestResult);
		return result;
	}
}
