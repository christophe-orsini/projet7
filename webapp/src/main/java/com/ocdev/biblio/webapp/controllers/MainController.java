package com.ocdev.biblio.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ocdev.biblio.webapp.config.PropertiesConfigurationService;

@Controller
public class MainController
{
	@Autowired PropertiesConfigurationService properties;
	@Autowired RestTemplateBuilder restTemplateBuilder;
	
	@GetMapping({"/login", "/", "/index" , "/logout"})
	public String login(Model model)
	{	
		return "/login";
	}
}
