package com.liping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.liping.domain.IamUser;
import com.liping.exception.ServiceException;
import com.liping.service.TokenInterface;
import com.liping.utils.ErrorConstans;
import com.liping.utils.JWTUtil;

@Component
public class TokenServiceImpl implements TokenInterface
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class.getName());
	public String getToken(IamUser user) throws ServiceException{
		if(user == null || !user.isValid()){
			LOGGER.error("parameter is invalid");
			throw new ServiceException(ErrorConstans.INVALID_PARAMETER_ERROR, "parameter is invalid");
		}
		try{
			String token =  JWTUtil.getToken(user);
			if(token == null){
				LOGGER.error("token is null");
				throw new ServiceException(ErrorConstans.NULL_PARAMETER_ERROR, "token is null");
			}
			return token;
		}catch(ServiceException sE){
			throw sE;
		}catch(Exception e){
			LOGGER.error("get token error");
			throw new ServiceException(ErrorConstans.INVALID_PARAMETER_ERROR, "get token error");
		}
	}
}
