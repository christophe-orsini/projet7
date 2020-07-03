package com.ocdev.biblio.apibiblio.services;

import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;

/**
 * Interface de déclaration des services pour les utilisateurs
 * @author C.Orsini
 */
public interface UtilisateurService
{
	
	public Utilisateur inscrire(String email, String password, String nom, String prenom) throws NullOrEmptyArgumentException;
	
}
