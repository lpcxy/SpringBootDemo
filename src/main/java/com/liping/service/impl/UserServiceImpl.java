package com.liping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.liping.domain.IamUser;
import com.liping.exception.ServiceException;
import com.liping.mapper.IamUserMapper;
import com.liping.service.UserInterface;
import com.liping.utils.ErrorConstans;

@Component
public class UserServiceImpl implements UserInterface
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class.getName());
	@Autowired
	private IamUserMapper iamUserMapper;
	
	@Override
	public IamUser queryUser(String name) throws ServiceException{
		try
		{
			return iamUserMapper.query(name);
		} catch (Exception e)
		{
			LOGGER.error("query user error, error message: " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(ErrorConstans.QUERY_USER_ERROR, "query user error");
		}
	}
	@Override
	public void updateUser(IamUser user) throws ServiceException{
		try{
			IamUser mysqlUser = iamUserMapper.query(user.getName());
			if(mysqlUser == null){
				iamUserMapper.insert(user);
			}else{
				iamUserMapper.update(user.getName(), user.getPassword());
			}
		}catch(Exception e){
			LOGGER.error("update user error");
			throw new ServiceException(ErrorConstans.UPDATE_IAM_USER_ERROR, "update user error.");
		}
	}
	@Override
	public void deleteUser(String name) throws ServiceException{
		try{
			iamUserMapper.delete(name);
		}catch(Exception e){
			LOGGER.error("delete user error");
			throw new ServiceException(ErrorConstans.DELETE_USER_ERROR, "delete user error.");
		}
	}
	@Override
	public void registerIamUser(IamUser user) throws ServiceException
	{
		// TODO Auto-generated method stub
		try{
			iamUserMapper.insert(user);
		}catch(Exception e){
			LOGGER.error("register user error");
			throw new ServiceException(ErrorConstans.REGISTER_IAM_USER_ERROR, "register user error");
		}
	}
}
