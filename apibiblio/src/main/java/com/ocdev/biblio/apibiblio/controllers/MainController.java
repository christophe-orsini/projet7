package com.ocdev.biblio.apibiblio.controllers;

import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class MainController
{
	@ApiOperation(value = "Obtenir les authorisations pour un utilisateur", 
			notes = "Vérifier et obtenir Les authorisations d'un utilisateur")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'utilisateur est authentifié"),
			@ApiResponse(code = 401, message = "Le login et/ou le mot de passe sont incorrects"),
			@ApiResponse(code = 403, message = "L'URI necessite une Basic Authentication")
			})
	@GetMapping(value = "/login", produces = "application/json")
	public ResponseEntity<Principal> verifierUtilisateur(Principal principal)
	{
		if (principal == null)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return new ResponseEntity<Principal>(principal, HttpStatus.OK);
	}
}
