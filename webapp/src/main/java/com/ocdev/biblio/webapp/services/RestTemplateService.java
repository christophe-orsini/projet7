package com.ocdev.biblio.webapp.services;

import org.springframework.stereotype.Service;

@Service
public class RestTemplateService
{
	private static String _login;
	private static String _password;
	
	public String getLogin()
	{
		return _login;
	}
	public void setLogin(String login)
	{
		_login = login;
	}
	public String getPassword()
	{
		return _password;
	}
	public void setPassword(String password)
	{
		_password = password;
	}
	
	

}
