package com.ocdev.biblio.batch.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesConfigurationService
{
	@Value("${app.serveur.apiurl}") // injection via application.properties
	private String url="";

	public String getApiUrl()
	{
		if (url.endsWith("/")) return url;
		return url + "/";
	}
	
	@Value("${app.serveur.login}")
	private String login="";

	public String getLogin()
	{
		return login;
	}
	
	@Value("${app.serveur.password}")
	private String password="";

	public String getPassword()
	{
		return password;
	}
}
