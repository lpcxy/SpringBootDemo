package com.liping.service;

import com.liping.domain.IamUser;
import com.liping.exception.ServiceException;

public interface TokenInterface
{
	String getToken(IamUser user) throws ServiceException;
}
