package com.ocdev.biblio.webapp.services;

import com.ocdev.biblio.webapp.dto.UtilisateurDto;
import com.ocdev.biblio.webapp.errors.BiblioException;
import com.ocdev.biblio.webapp.objects.Utilisateur;

public interface UtilisateurService
{
	Utilisateur inscription(UtilisateurDto utilisateurDto) throws BiblioException;
}
