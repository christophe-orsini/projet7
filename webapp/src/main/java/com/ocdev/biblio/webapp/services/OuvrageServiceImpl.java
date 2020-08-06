package com.ocdev.biblio.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ocdev.biblio.webapp.dto.OuvrageCriteria;
import com.ocdev.biblio.webapp.entities.Ouvrage;
import com.ocdev.biblio.webapp.utils.RestResponsePage;

@Service
public class OuvrageServiceImpl implements OuvrageService
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired RestTemplateService restTemplateService;

	@Override
	public Ouvrage getOuvrageById(Long id)
	{
	 	RestTemplate restTemplate = restTemplateService.buildRestTemplate();
	 	return restTemplate.getForObject(properties.getApiUrl() + "ouvrages/" +  id, Ouvrage.class);
	}

	@Override
	public RestResponsePage<Ouvrage> listeOuvrages(OuvrageCriteria ouvrageCriteria, int page, int taille)
	{
	 	RestTemplate restTemplate = restTemplateService.buildRestTemplate();
	 	
		ParameterizedTypeReference<RestResponsePage<Ouvrage>> responseType = 
				new ParameterizedTypeReference<RestResponsePage<Ouvrage>>() { };

		HttpEntity<OuvrageCriteria> entity = new HttpEntity<OuvrageCriteria>(ouvrageCriteria);
		ResponseEntity<RestResponsePage<Ouvrage>> result = restTemplate.exchange(
				properties.getApiUrl() + "ouvrages/search", HttpMethod.POST, entity, responseType);
		
		return result.getBody();
	}
}
