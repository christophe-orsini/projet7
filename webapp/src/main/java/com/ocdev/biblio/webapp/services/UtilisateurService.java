package com.ocdev.biblio.webapp.services;

import com.ocdev.biblio.webapp.dto.UtilisateurDto;
import com.ocdev.biblio.webapp.entities.Utilisateur;
import com.ocdev.biblio.webapp.errors.BiblioException;

public interface UtilisateurService
{
	Utilisateur inscription(UtilisateurDto utilisateurDto) throws BiblioException;
}
