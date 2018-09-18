package com.liping.utils;

import com.liping.console.RequestError;
import com.liping.console.RequestResult;
import com.liping.exception.ServiceException;

public class ErrorUtil
{
	public static void handleException(RequestResult requestResult, Exception e){
		requestResult.setStatus(RequestResult.FAILED);
		if(e instanceof ServiceException){
			ServiceException sException = (ServiceException)e;
			RequestError error = new RequestError(sException.getCode(), sException.getMessage());
			requestResult.setError(error);
		}else{
			RequestError error = new RequestError("UNKONW", e.getMessage());
			requestResult.setError(error);
		}
	}
}
