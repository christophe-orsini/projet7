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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/*")
public class UtilisateurController
{
	@Autowired UtilisateurService utilisateurService;
	
	@ApiOperation(value = "Inscription d'un utilisateur", notes = "Inscription d'un nouvel utilisateur de l'application")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "L'utilisateur est correctement inscrit"),
			@ApiResponse(code = 409, message = "Un utilisateur avec le même email existe déjà")
			})
	@PostMapping(value = "/utilisateurs")
	public ResponseEntity<UtilisateurDto> inscrire(@Valid @RequestBody UtilisateurDto utilisateurDto) throws AlreadyExistsException
	{
		UtilisateurDto result = utilisateurService.creer(utilisateurDto);
		return new ResponseEntity<UtilisateurDto>(result, HttpStatus.CREATED);
	}
}
