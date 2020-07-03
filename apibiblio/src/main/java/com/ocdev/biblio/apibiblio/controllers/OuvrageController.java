package com.ocdev.biblio.apibiblio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ocdev.biblio.apibiblio.assemblers.IAssembler;
import com.ocdev.biblio.apibiblio.dto.OuvrageDetail;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;
import com.ocdev.biblio.apibiblio.exceptions.RequiredAndNotFoundException;
import com.ocdev.biblio.apibiblio.services.OuvrageService;

@RestController
@RequestMapping("/api/v1/*")
public class OuvrageController
{
	@Autowired private OuvrageService ouvrageService;
	@Autowired private IAssembler<Ouvrage, OuvrageDetail> ouvrageAssembler;
	
	@PostMapping(value ="/ouvrages")
	public ResponseEntity<OuvrageDetail> createOuvrage(@RequestBody final OuvrageDetail ouvrageDetail)
	{
		OuvrageDetail newOuvrage = null;
		
		try
		{
			Ouvrage ouvrage = ouvrageService.ajouterOuChanger(ouvrageDetail.getTitre(), ouvrageDetail.getResume(),
					ouvrageDetail.getAuteur(), ouvrageDetail.getAnneeParution(), ouvrageDetail.getTheme(), 0);
			newOuvrage = ouvrageAssembler.createDto(ouvrage);
		}
		catch (NullOrEmptyArgumentException e)
		{
			return new ResponseEntity<OuvrageDetail>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<OuvrageDetail>(newOuvrage, HttpStatus.CREATED);
	}
	
	@PutMapping(value ="/ouvrages")
	public ResponseEntity<OuvrageDetail> updateOuvrage(@RequestBody final OuvrageDetail ouvrageDetail)
	{
		OuvrageDetail newOuvrage = null;
		
		try
		{
			Ouvrage ouvrage = ouvrageService.ajouterOuChanger(ouvrageDetail.getTitre(), ouvrageDetail.getResume(),
					ouvrageDetail.getAuteur(), ouvrageDetail.getAnneeParution(), ouvrageDetail.getTheme(), ouvrageDetail.getNbreExemplaire());
			newOuvrage = ouvrageAssembler.createDto(ouvrage);
		}
		catch (NullOrEmptyArgumentException e)
		{
			return new ResponseEntity<OuvrageDetail>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<OuvrageDetail>(newOuvrage, HttpStatus.OK);
	}
	
	@GetMapping(value = "/ouvrages/{ouvrageId}")
	public ResponseEntity<OuvrageDetail> getOuvrageById(@PathVariable final long ouvrageId)
	{
		try
		{
			 Ouvrage ouvrage = ouvrageService.consulterOuvrage(ouvrageId);
			 return new ResponseEntity<OuvrageDetail>(ouvrageAssembler.createDto(ouvrage), HttpStatus.OK);
		}
		catch (NullOrEmptyArgumentException | RequiredAndNotFoundException e)
		{
			return new ResponseEntity<OuvrageDetail>(HttpStatus.NOT_FOUND);
		}
	}
}
