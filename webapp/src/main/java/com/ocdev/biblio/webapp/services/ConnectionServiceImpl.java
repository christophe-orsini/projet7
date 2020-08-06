package com.ocdev.biblio.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectionServiceImpl implements ConnectionService
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired RestTemplateService restTemplateService;

	@Override
	public boolean checkLogin(String userName, String password)
	{
		RestTemplate restTemplate = restTemplateService.buildRestTemplate(userName, password);
		ResponseEntity<String> response = null;
		try
		{
			response = restTemplate.postForEntity(properties.getApiUrl() + "checklogin", null, String.class);
		}
		catch (HttpClientErrorException e)
		{
			// TODO
			// Log erreur appel API
		}
		
		if (response!= null &&  response.getStatusCode() == HttpStatus.OK)
		{
			return true;
		}
		return false;
	}

}
