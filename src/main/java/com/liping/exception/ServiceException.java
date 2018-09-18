package com.liping.exception;

public class ServiceException extends Exception
{
	private String code;
	private String message;
	public ServiceException(){super();}
	public ServiceException(String code, String message){
		super();
		this.code = code;
		this.message = message;
	}
	@Override
	public String toString(){
		return "[code: " + code + ", message: " + message + "]";
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	
}
