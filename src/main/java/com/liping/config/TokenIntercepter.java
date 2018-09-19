package com.liping.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.liping.utils.TokenFilter;

public class TokenIntercepter implements HandlerInterceptor
{
	@Override
	public void afterCompletion(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object o, Exception arg3)
			throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object o, ModelAndView arg3)
			throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object o) throws Exception
	{
		// TODO Auto-generated method stub
		return TokenFilter.doFilter(httpRequest, httpResponse, o);
	}

}
