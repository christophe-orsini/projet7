package com.ocdev.biblio.apibiblio.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ocdev.biblio.apibiblio.dto.UtilisateurDto;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.services.UtilisateurService;

@RestController
@RequestMapping("/api/v1/*")
public class UtilisateurController
{
	@Autowired UtilisateurService utilisateurService;
	
	@PostMapping(value = "/utilisateurs")
	public ResponseEntity<UtilisateurDto> inscrire(@Valid @RequestBody UtilisateurDto utilisateurDto) throws AlreadyExistsException
	{
		UtilisateurDto result = utilisateurService.creer(utilisateurDto);
		return ResponseEntity.ok(result);
	}
}
