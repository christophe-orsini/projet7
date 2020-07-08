package com.ocdev.biblio.apibiblio.services;

import com.ocdev.biblio.apibiblio.dto.UtilisateurDto;
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
	 * @return Le DTO de l'utilisateur créé
	 * @throws AlreadyExistsException
	 */
	public UtilisateurDto creer(UtilisateurDto utilisateurDto) throws AlreadyExistsException;
	
}
