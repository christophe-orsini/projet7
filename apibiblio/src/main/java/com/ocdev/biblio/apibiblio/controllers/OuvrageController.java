package com.ocdev.biblio.apibiblio.controllers;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ocdev.biblio.apibiblio.criterias.OuvrageCriteria;
import com.ocdev.biblio.apibiblio.dto.OuvrageCreateDto;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.services.OuvrageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/*")
@Validated
public class OuvrageController
{
	@Autowired private OuvrageService ouvrageService;
	
	@ApiOperation(value = "Ajout d'un ouvrage", notes = "Ajout d'un nouvel ouvrage")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "L'ouvrage est correctement créé"),
			@ApiResponse(code = 460, message = "Un ouvrage avec le même titre existe déjà")
			})
	@PostMapping(value ="/ouvrages")
	public ResponseEntity<Ouvrage> ajouterOuvrage(@Valid @RequestBody final OuvrageCreateDto ouvrageCreateDto) throws AlreadyExistsException
	{
		Ouvrage result = ouvrageService.creer(ouvrageCreateDto);
		return new ResponseEntity<Ouvrage>(result, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Consultation d'un ouvrage", notes = "Obtenir les détails d'un ouvrage")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'ouvrage trouvé est retourné dans le corps de la réponse"),
			@ApiResponse(code = 404, message = "L'ouvrage avec cet ID n'existe pas")
			})
	@GetMapping(value = "/ouvrages/{ouvrageId}")
	public ResponseEntity<Ouvrage> getOuvrageById(@ApiParam(value="ID de l'ouvrage", required = true, example = "1")
			@PathVariable @Min(1) final long ouvrageId) throws EntityNotFoundException
	{
		Ouvrage ouvrage = ouvrageService.consulterOuvrage(ouvrageId);
		return new ResponseEntity<Ouvrage>(ouvrage, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Recherche d'ouvrages", notes = "Obtenir une liste d'ouvrages correspondant à plusieurs critères de recherche")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des ouvrages est retourné dans le corps de la réponse")
			})
	@GetMapping(value = "/ouvrages")
	public ResponseEntity<Collection<Ouvrage>> rechercherOuvrages(@RequestBody final OuvrageCriteria ouvrageCriteria, 
			@RequestParam(required = false, defaultValue = "0") int page)
	{
		return new ResponseEntity<Collection<Ouvrage>>(HttpStatus.NOT_FOUND);
	}
}
