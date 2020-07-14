package com.ocdev.biblio.apibiblio.controllers;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocdev.biblio.apibiblio.dto.ThemeCreateDto;
import com.ocdev.biblio.apibiblio.entities.Theme;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.services.ThemeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/*")
@Validated
public class ThemeController
{
	@Autowired private ThemeService themeService;
	
	@ApiOperation(value = "Ajout d'un thème", notes = "Ajout d'un nouveau thème")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Le thème est correctement créé"),
			@ApiResponse(code = 409, message = "Un thème avec le même nom existe déjà")
			})
	@PostMapping(value ="/themes")
	public ResponseEntity<Theme> ajouterTheme(@Valid @RequestBody final ThemeCreateDto themeCreateDto) throws AlreadyExistsException
	{
		Theme result = themeService.creer(themeCreateDto);
		return new ResponseEntity<Theme>(result, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Liste des thèmes", notes = "Obtenir la liste des thèmes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des thèmes est retournée dans le corps de la réponse"),
			})
	@GetMapping(value = "/themes")
	public ResponseEntity<Collection<Theme>> getThemes() throws EntityNotFoundException
	{
		Collection<Theme> themes = themeService.listeThemes();
		return new ResponseEntity<Collection<Theme>>(themes, HttpStatus.OK);
	}
}
