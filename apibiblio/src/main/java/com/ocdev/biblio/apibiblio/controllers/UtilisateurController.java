package com.ocdev.biblio.apibiblio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ocdev.biblio.apibiblio.assemblers.UtilisateurAssembler;
import com.ocdev.biblio.apibiblio.dto.UtilisateurDetail;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;
import com.ocdev.biblio.apibiblio.services.UtilisateurService;

@RestController
@RequestMapping("/api/v1/*")
public class UtilisateurController
{
	@Autowired UtilisateurService utilisateurService;
	@Autowired UtilisateurAssembler utilisateurAssembler;
	
	@PostMapping(value = "/utilisateurs")
	public ResponseEntity<UtilisateurDetail> creerUtilisateur(@RequestBody final UtilisateurDetail utilisateurDetail)
	{
			UtilisateurDetail newUtilisateur = null;
		
		try
		{
			Utilisateur utilisateur = utilisateurService.inscrire(utilisateurDetail.getEmail(), utilisateurDetail.getPassword(),
					utilisateurDetail.getNom(), utilisateurDetail.getPrenom());
			newUtilisateur = utilisateurAssembler.createDto(utilisateur);
		}
		catch (NullOrEmptyArgumentException e)
		{
			return new ResponseEntity<UtilisateurDetail>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<UtilisateurDetail>(newUtilisateur, HttpStatus.CREATED);
	}
}
