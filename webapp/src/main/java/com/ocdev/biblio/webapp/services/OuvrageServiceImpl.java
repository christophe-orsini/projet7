package com.ocdev.biblio.webapp.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ocdev.biblio.webapp.config.PropertiesConfigurationService;
import com.ocdev.biblio.webapp.entities.Ouvrage;

@Service
public class OuvrageServiceImpl implements OuvrageService
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired RestTemplateBuilder restTemplateBuilder;
	@Autowired RestTemplateService restTemplateService;
	
	private String username = restTemplateService.getLogin();
	private String password = restTemplateService.getPassword();
	private RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(username, password).build();
	
	@Override
	public Ouvrage getOuvrageById(Long id)
	{
		return restTemplate.getForObject(properties.getApiUrl() + "ouvrages/" +  id, Ouvrage.class);
	}

	@Override
	public Collection<Ouvrage> listeOuvrages(Long id)
	{
		return restTemplate.getForObject(properties.getApiUrl() + "ouvrages?page=1&taille=2", Page<Ouvrage>.class);
	}
}
