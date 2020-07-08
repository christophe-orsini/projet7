package com.ocdev.biblio.apibiblio.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ocdev.biblio.apibiblio.dto.UtilisateurDto;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.services.UtilisateurService;
import com.ocdev.biblio.apibiblio.utils.Tools;

@RestController
@RequestMapping("/api/v1/*")
public class UtilisateurController
{
	@Autowired UtilisateurService utilisateurService;
	
	
	@PostMapping(value = "/utilisateurs")
	public ResponseEntity<UtilisateurDto> inscrire(@Valid @RequestBody final UtilisateurDto utilisateurDto)
	{
		// verifier les entrées
		if (Tools.stringIsNullOrEmpty(utilisateurDto.getEmail()) || Tools.stringIsNullOrEmpty(utilisateurDto.getPassword()) ||
				Tools.stringIsNullOrEmpty(utilisateurDto.getNom()))
		{
			return new ResponseEntity<UtilisateurDto>(HttpStatus.BAD_REQUEST);
		}
		
		UtilisateurDto result = null;
		try
		{
			result = utilisateurService.creer(utilisateurDto);
		}
		catch (AlreadyExistsException e)
		{
			return new ResponseEntity<UtilisateurDto>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<UtilisateurDto>(result, HttpStatus.CREATED);	
	}
}
