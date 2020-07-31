package com.ocdev.biblio.apibiblio.dto;

import com.ocdev.biblio.apibiblio.entities.Role;

public class UserCredentialsResponse
{
	private String userName;
	private String password;
	private Role role;
	
	public UserCredentialsResponse()
	{
		super();
	}
	
	public UserCredentialsResponse(String userName, String password, Role role)
	{
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public Role getRole()
	{
		return role;
	}
	
	public void setRole(Role role)
	{
		this.role = role;
	}
}
