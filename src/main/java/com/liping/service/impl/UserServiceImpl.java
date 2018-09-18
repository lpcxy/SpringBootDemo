package com.liping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liping.domain.User;
import com.liping.exception.ServiceException;
import com.liping.mapper.UserMapper;
import com.liping.service.UserInterface;
import com.liping.utils.ErrorConstans;

@Component
public class UserServiceImpl implements UserInterface
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class.getName());
	@Autowired
	private UserMapper userMapper;
	@Override
	public User queryUserById(String id) throws ServiceException{
		try
		{
			return userMapper.queryById(id);
		} catch (Exception e)
		{
			LOGGER.error("query user error, error message: " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(ErrorConstans.QUERY_USER_ERROR, "query user error");
		}
	}
	@Override
	public void updateUser(User user) throws ServiceException{
		try{
			User mysqlUser = userMapper.queryById(user.getId());
			if(mysqlUser == null){
				userMapper.insert(user);
			}else{
				userMapper.update(user);
			}
		}catch(Exception e){
			LOGGER.error("insert user error");
			throw new ServiceException(ErrorConstans.INSERT_USER_ERROR, "insert user error.");
		}
	}
	@Override
	public void deleteUser(String id) throws ServiceException{
		try{
			userMapper.delete(id);
		}catch(Exception e){
			LOGGER.error("delete user error");
			throw new ServiceException(ErrorConstans.DELETE_USER_ERROR, "delete user error.");
		}
	}
}
