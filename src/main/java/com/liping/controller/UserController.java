package com.liping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liping.console.RequestResult;
import com.liping.domain.User;
import com.liping.exception.ServiceException;
import com.liping.service.UserInterface;
import com.liping.utils.CommonConstans;
import com.liping.utils.ErrorConstans;
import com.liping.utils.ErrorUtil;
import com.liping.utils.JsonUtil;

@RestController
@RequestMapping("v1")
public class UserController
{
	@Autowired
	private UserInterface userService;
	@RequestMapping(value="/users/{id}", produces = CommonConstans.APPLICATION_JSON,method = RequestMethod.GET)
	public String users(@PathVariable String id){
		RequestResult requestResult = new RequestResult();
		try{
			 User user = userService.queryUserById(id);
			 if(user != null){
				 List<Object> results = new ArrayList<Object>();
				 results.add(user);
				 requestResult.setResults(results);
			 }
		}catch(Exception e){
			ErrorUtil.handleException(requestResult, e);
		}
		String result = JsonUtil.toJson(requestResult);
		return result;
	}
	
	@RequestMapping(value="/users", produces = CommonConstans.APPLICATION_JSON,method = RequestMethod.POST)
	public String updateUser(@RequestBody String userJson){
		RequestResult requestResult = new RequestResult();
		try{
			 if(userJson == null){
				 throw new ServiceException(ErrorConstans.NULL_PARAMETER_ERROR, "parameter is null");
			 }
			 User user = JsonUtil.parseObject(userJson, User.class);
			 userService.updateUser(user);
		}catch(Exception e){
			ErrorUtil.handleException(requestResult, e);
		}
		String result = JsonUtil.toJson(requestResult);
		return result;
	}
	
	@RequestMapping(value="/users/{id}", produces = CommonConstans.APPLICATION_JSON,method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable String id){
		RequestResult requestResult = new RequestResult();
		try{
			 if(id == null){
				 throw new ServiceException(ErrorConstans.NULL_PARAMETER_ERROR, "parameter is null");
			 }
			 userService.deleteUser(id);
		}catch(Exception e){
			ErrorUtil.handleException(requestResult, e);
		}
		String result = JsonUtil.toJson(requestResult);
		return result;
	}
}
