package com.ocdev.biblio.webapp.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ocdev.biblio.webapp.entities.Ouvrage;
import com.ocdev.biblio.webapp.services.OuvrageService;

@Controller
public class OuvrageController
{
	@Autowired OuvrageService ouvrageService;
	
	@GetMapping("/abonne/listeOuvrages")
	public String listeOuvrages(Model model, Principal utilisateur)
	{
		Ouvrage response = ouvrageService.getOuvrageById(1L);
		model.addAttribute("ouvrage", response);
		return "/ouvrage/listeOuvrages";
	}
	
	@GetMapping("/abonne/consulterOuvrage/{id}")
	public String consulter(Model model, Principal utilisateur)
	{
		Ouvrage response = ouvrageService.getOuvrageById(1L);
		model.addAttribute("ouvrage", response);
		return "/ouvrage/detailOuvrage";
	}
}
