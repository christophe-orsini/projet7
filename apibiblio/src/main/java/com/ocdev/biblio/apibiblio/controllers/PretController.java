package com.ocdev.biblio.apibiblio.controllers;

import java.util.Collection;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ocdev.biblio.apibiblio.entities.Pret;

@RestController
@RequestMapping("/api/v1/*")
public class PretController
{
	@GetMapping(value = "/prets/user/{userName}")
	public ResponseEntity<Collection<Pret>> ListeMesPrets(@PathVariable final String nom)
	{
		throw new NotYetImplementedException();
	}
	
	@PostMapping(value = "/prets")
	public ResponseEntity<Pret> createPret(@RequestBody final Pret pret)
	{
		throw new NotYetImplementedException();
	}
	
	@PutMapping(value ="/prets")
	public ResponseEntity<Pret> retourPret(@RequestBody final Pret pret)
	{
		throw new NotYetImplementedException();
	}
	
	@PutMapping(value ="/prets/{pretId}")
	public ResponseEntity<Pret> prolongePret(@PathVariable final Long pretId, @RequestBody final Pret pret)
	{
		throw new NotYetImplementedException();
	}
}
