package com.liping.service;

import com.liping.domain.User;
import com.liping.exception.ServiceException;

public interface UserInterface
{
	User queryUserById(String id) throws ServiceException;
	void updateUser(User user) throws ServiceException;
	void deleteUser(String id) throws ServiceException;
}
