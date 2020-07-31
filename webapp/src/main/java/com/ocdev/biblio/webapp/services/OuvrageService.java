package com.ocdev.biblio.webapp.services;

import org.springframework.data.domain.Page;

import com.ocdev.biblio.webapp.dto.OuvrageCriteria;
import com.ocdev.biblio.webapp.entities.Ouvrage;

public interface OuvrageService
{
	public Page<Ouvrage> listeOuvrages(OuvrageCriteria ouvrageCriteria, int page, int taille);
	public Ouvrage getOuvrageById(Long id);
}
