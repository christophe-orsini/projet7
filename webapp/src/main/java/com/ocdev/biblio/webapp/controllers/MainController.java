package com.ocdev.biblio.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.ocdev.biblio.webapp.services.ConnectionService;

@Controller
public class MainController
{
	@Autowired ConnectionService connectionService;
		
	@GetMapping({"/login", "/", "/index" , "/logout"})
	public String login(Model model)
	{	
		return "/login";
	}
	
	@PostMapping("/checklogin")
	public String checklogin(String username, String password, Model model)
	{	
		if (connectionService.checkLogin(username, password))
		{
			return "/abonne/listeOuvrages";
		}
		
		return "/login";
	}
}
