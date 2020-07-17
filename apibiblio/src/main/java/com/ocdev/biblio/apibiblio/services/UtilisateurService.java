package com.ocdev.biblio.apibiblio.services;

import com.ocdev.biblio.apibiblio.dto.UtilisateurCreateDto;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;

/**
 * Interface de déclaration des services pour les utilisateurs
 * @author C.Orsini
 */
public interface UtilisateurService
{
	/**
	 * Méthode de création d'un utilisateur.
	 * @param utilisateurDto Le DTO de l'utilisateur à créer
	 * @return L'utilisateur créé
	 * @throws AlreadyExistsException levée si un utilisateur avec le même email (login) existe déjà
	 */
	public Utilisateur creer(UtilisateurCreateDto utilisateurDto) throws AlreadyExistsException;
	
}
