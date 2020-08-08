package com.ocdev.biblio.batch.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ocdev.biblio.batch.model.Pret;

@Service
public class PretServiceImpl implements PretService
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired RestTemplateService restTemplateService;
	
	@Override
	public Collection<Pret> listePretsEnRetard(Date dateRetard)
	{
	RestTemplate restTemplate = restTemplateService.buildRestTemplate();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		ParameterizedTypeReference<Collection<Pret>> responseType = 
				new ParameterizedTypeReference<Collection<Pret>>() { };
				
		ResponseEntity<Collection<Pret>> result = restTemplate.exchange(
				properties.getApiUrl() + "prets/retard?dateMaxi=" + format.format(dateRetard), 
				HttpMethod.GET, null, responseType);
		
		if (result.getStatusCode() == HttpStatus.OK)
		{
			return result.getBody();
		}
		
		return new ArrayList<Pret>();
	}
}
