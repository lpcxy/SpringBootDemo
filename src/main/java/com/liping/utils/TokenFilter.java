package com.liping.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.liping.domain.IamUser;

@Component
public abstract class TokenFilter
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class.getName());
	public static boolean doFilter(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object o){
		if(httpRequest == null || httpResponse == null){
			return false;
		}
		String url = httpRequest.getRequestURI();
		if(CommonConstans.GET_TOKEN_URL.equals(url) || 
				CommonConstans.REGISTER_IAM_USER_URL.equals(url)){		
			return true;
		}
		String token = httpRequest.getHeader(CommonConstans.TOKEN_HEADER);
		try{
			IamUser user = JWTUtil.parseToken(token);
			TokenInfo.setIamUser(user);
			TokenInfo.setUserId(user.getId());
			TokenInfo.setUserName(user.getName());
			TokenInfo.setRoles(user.getRoles());
			return true;
		}catch(Exception e){
			LOGGER.equals("token is invalid");
			httpResponse.setStatus(ErrorConstans.FORBIDEN);
			return false;
		}
	}
}
