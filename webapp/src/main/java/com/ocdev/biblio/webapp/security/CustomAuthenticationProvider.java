package com.ocdev.biblio.webapp.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.ocdev.biblio.webapp.entities.Role;
import com.ocdev.biblio.webapp.services.PropertiesConfigurationService;
import com.ocdev.biblio.webapp.services.RestTemplateService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired RestTemplateService restTemplateService;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
    	String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        RestTemplate restTemplate = restTemplateService.buildRestTemplate();
        
        ResponseEntity<String> userResponse = null;
    	try
		{
    		userResponse = restTemplate.getForEntity(properties.getApiUrl() + "checklogin", String.class);
		}
		catch (RestClientException e)
		{
			throw new BadCredentialsException("External system authentication failed");
		}
		if (userResponse == null) throw new BadCredentialsException("External system authentication failed");
        
        restTemplateService.setLogin(login);
        restTemplateService.setPassword(password);
        
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Role.ROLE_ABONNE.toString()));
		
        return new UsernamePasswordAuthenticationToken(login, password, authorities); 
    }
 
    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
