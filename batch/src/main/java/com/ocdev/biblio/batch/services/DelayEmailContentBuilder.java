package com.ocdev.biblio.batch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.ocdev.biblio.batch.model.Utilisateur;

@Service
public class DelayEmailContentBuilder
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired TemplateEngine templateEngine;
	
	public String build(Utilisateur abonne)
	{
		Context context = new Context();
        context.setVariable("abonne", abonne);
        context.setVariable("email", properties.emailContact());
        context.setVariable("notreSite", properties.urlWebApp());
        
        return templateEngine.process("email", context);
	}
}
