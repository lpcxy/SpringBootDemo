package com.liping.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonUtil
{
	public static String toJson(Object object){
		return JSON.toJSONString(object);
	}
	
	public static <T> T parseObject(String jsonText, Class<T> classT){
		return JSON.parseObject(jsonText, classT);
	}
	
	public static List parseObjectList(String jsonText, Class<?> classT){
		return JSON.parseArray(jsonText, classT);
	}
}
