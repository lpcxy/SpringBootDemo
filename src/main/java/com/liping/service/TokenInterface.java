package com.liping.service;

import javax.servlet.http.HttpServletResponse;

import com.liping.domain.IamUser;
import com.liping.exception.ServiceException;

public interface TokenInterface
{
	String getToken(IamUser user, HttpServletResponse httpResponse) throws ServiceException;
}
