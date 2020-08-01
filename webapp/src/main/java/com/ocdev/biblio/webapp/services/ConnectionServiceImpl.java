package com.ocdev.biblio.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectionServiceImpl implements ConnectionService
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired RestTemplateService restTemplateService;

	@Override
	public boolean checkLogin(String userName, String Password)
	{
		RestTemplate restTemplate = restTemplateService.buildRestTemplate();
		ResponseEntity<String> response;
		response = restTemplate.postForEntity(properties.getApiUrl() + "checkLogin", null, String.class);
		
		return true;
	}

}
