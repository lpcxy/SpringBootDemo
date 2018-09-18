package com.liping.console;

public class RequestResult
{
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	private String status;
	private Object results;
	private RequestError error;
	public RequestResult(){
		this.status = SUCCESS;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public Object getResults()
	{
		return results;
	}
	public void setResults(Object results)
	{
		this.results = results;
	}
	public RequestError getError()
	{
		return error;
	}
	public void setError(RequestError error)
	{
		this.error = error;
	}
}
