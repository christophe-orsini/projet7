package com.ocdev.biblio.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService
{
	@Autowired RestTemplateBuilder restTemplateBuilder;
	
	private static String _login;
	private static String _password;
	
	public RestTemplateService() 
	{
		super();
	}
	
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
	
	public RestTemplate buildRestTemplate(String login, String password)
	{
		_login = login;
		_password = password;
		
	 	return restTemplateBuilder.basicAuthentication(_login, _password).build();
	}

	public RestTemplate buildRestTemplate()
	{
	 	return restTemplateBuilder.basicAuthentication(_login, _password).build();
	}
}
