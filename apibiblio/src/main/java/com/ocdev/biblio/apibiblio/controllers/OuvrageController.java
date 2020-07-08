package com.ocdev.biblio.apibiblio.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ocdev.biblio.apibiblio.dto.OuvrageDto;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.services.OuvrageService;

@RestController
@RequestMapping("/api/v1/*")
public class OuvrageController
{
	@Autowired private OuvrageService ouvrageService;
	
	@PostMapping(value ="/ouvrages")
	public ResponseEntity<OuvrageDto> ajouterOuvrage(@RequestBody final OuvrageDto ouvrageDto)
	{
		OuvrageDto result = null;
		try
		{
			result = ouvrageService.creer(ouvrageDto);
		}
		catch (AlreadyExistsException e)
		{
			return new ResponseEntity<OuvrageDto>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<OuvrageDto>(result, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/ouvrages/{ouvrageId}")
	public ResponseEntity<OuvrageDto> getOuvrageById(@PathVariable final long ouvrageId)
	{
		try
		{
			 OuvrageDto ouvrageDto = ouvrageService.consulterOuvrage(ouvrageId);
			 return new ResponseEntity<OuvrageDto>(ouvrageDto, HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<OuvrageDto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/ouvrages")
	public ResponseEntity<Collection<OuvrageDto>> rechercherOuvrages(@RequestBody final OuvrageDto ouvrageDto, 
			@RequestParam(required = false, defaultValue = "0") int page)
	{
		return new ResponseEntity<Collection<OuvrageDto>>(HttpStatus.NOT_FOUND);
	}
}
