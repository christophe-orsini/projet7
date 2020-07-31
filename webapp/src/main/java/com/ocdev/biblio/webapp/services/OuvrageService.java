package com.ocdev.biblio.webapp.services;

import java.util.Collection;

import com.ocdev.biblio.webapp.entities.Ouvrage;

public interface OuvrageService
{
	public Collection<Ouvrage> listeOuvrages(Long id);
	public Ouvrage getOuvrageById(Long id);
}
