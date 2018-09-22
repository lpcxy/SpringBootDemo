package com.liping.domain;

import java.util.Date;
import java.util.List;

import com.liping.utils.UUIDGenerator;

public class IamUser
{
	private String id = UUIDGenerator.uuid();
	private String name;
	private String password;
	private List<String> roles;
	private Date expiration;
	public Date getExpiration()
	{
		return expiration != null ? (Date)expiration.clone() : null;
	}
	public void setExpiration(Date expiration)
	{
		this.expiration = (expiration != null ? (Date)expiration.clone() : null);
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public List<String> getRoles()
	{
		return roles;
	}
	public void setRoles(List<String> roles)
	{
		this.roles = roles;
	}
	/**
	 * 对象是否非法
	 * */
	public Boolean isValid(){
		return name != null && password != null;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == this) {
			return true;
		}
		if(!(o instanceof IamUser)){
			return false;
		}
		IamUser user = (IamUser)o;
		return user.id.equals(this.id) &&
				user.name.equals(this.name);
	}
	@Override
	public int hashCode(){
		int result = 17;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;

	}
}
