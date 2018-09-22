package com.liping.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liping.domain.IamUser;
import com.liping.exception.ServiceException;
import com.liping.mapper.IamUserMapper;
import com.liping.service.TokenInterface;
import com.liping.utils.ErrorConstans;
import com.liping.utils.JWTUtil;

@Component
public class TokenServiceImpl implements TokenInterface
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class.getName());
	
	@Autowired
	private IamUserMapper iamUserMapper;
	public String getToken(IamUser user, HttpServletResponse httpResponse) throws ServiceException{
		if(user == null || !user.isValid()){
			LOGGER.error("parameter is invalid");
			throw new ServiceException(ErrorConstans.INVALID_PARAMETER_ERROR, "parameter is invalid");
		}
		IamUser mysqlUser = iamUserMapper.query(user.getName());
		if(mysqlUser == null || !user.getPassword().equals(mysqlUser.getPassword())){
			LOGGER.error("invalid user");
			httpResponse.setStatus(ErrorConstans.FORBIDEN);
			throw new ServiceException(ErrorConstans.INVALID_PARAMETER_ERROR, "invalid user");
		}
		try{
			String token =  JWTUtil.getToken(mysqlUser);
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
