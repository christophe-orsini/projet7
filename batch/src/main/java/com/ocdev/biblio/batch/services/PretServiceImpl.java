package com.ocdev.biblio.batch.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
				properties.apiUrl() + "prets/retard?dateMaxi=" + format.format(dateRetard), 
				HttpMethod.GET, null, responseType);
		
		if (result.getStatusCode() != HttpStatus.OK)
		{
			return new ArrayList<Pret>();
			
		}
		
		return result.getBody();
	}
}
