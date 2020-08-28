package com.ocdev.biblio.batch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.ocdev.biblio.batch.model.Utilisateur;

@Service
public class EmailContentBuilder
{
	@Autowired TemplateEngine templateEngine;
	
	public String build(Utilisateur abonne)
	{
		Context context = new Context();
        context.setVariable("abonne", abonne);
        context.setVariable("email", "info@biblio.fr");
        context.setVariable("notreSite", "localhost:8084");
        
        return templateEngine.process("email", context);
	}
}
