package com.ocdev.biblio.webapp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ocdev.biblio.webapp.dto.UserCredentialsResponse;
import com.ocdev.biblio.webapp.services.PropertiesConfigurationService;
import com.ocdev.biblio.webapp.services.RestTemplateService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired RestTemplateBuilder restTemplateBuilder;
	@Autowired RestTemplateService restTemplateService;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
    	String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        UserCredentialsResponse user = restAuthenticate(login, password);
        if (user == null)
        {
        	throw new BadCredentialsException("External system authentication failed");
        }
        
        restTemplateService.setLogin(login);
        restTemplateService.setPassword(password);
        
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		
        return new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), authorities); 
    }
 
    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    private UserCredentialsResponse restAuthenticate(String login, String password)
    { 
    	RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(login, password).build();
		
    	UserCredentialsResponse userResponse = null;
    	try
		{
    		userResponse = restTemplate.getForObject(properties.getApiUrl() + "checklogin", UserCredentialsResponse.class);
		}
		catch (RestClientException e)
		{
			return null;
		}
		if (userResponse == null) return null;
		
		
		return userResponse;
    }
}
