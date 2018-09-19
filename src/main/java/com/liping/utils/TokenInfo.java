package com.liping.utils;

import java.util.List;

import com.liping.domain.IamUser;

public class TokenInfo
{
	public static ThreadLocal<IamUser> iamUser = new ThreadLocal<>();
	public static ThreadLocal<String> userId = new ThreadLocal<>();
	public static ThreadLocal<String> userName = new ThreadLocal<>();
	public static ThreadLocal<List<String>> roles = new ThreadLocal<>();
	
	public static IamUser getIamUser(){
		return iamUser.get();
	}
	public static String getUserId(){
		return userId.get();
	}
	public static String getUserName(){
		return userName.get();
	}
	public static List<String> getRoles(){
		return roles.get();
	}
	public static void setIamUser(IamUser user){
		iamUser.set(user);
	}
	public static void setUserId(String id){
		userId.set(id);
	}
	public static void setUserName(String name){
		userName.set(name);
	}
	public static void setRoles(List<String> roleList){
		roles.set(roleList);
	}
}
