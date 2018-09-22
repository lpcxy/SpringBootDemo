package com.liping.service;

import com.liping.domain.IamUser;
import com.liping.exception.ServiceException;

public interface UserInterface
{
	IamUser queryUser(String name) throws ServiceException;
	void updateUser(IamUser user) throws ServiceException;
	void deleteUser(String name) throws ServiceException;
	void registerIamUser(IamUser user) throws ServiceException;
}
