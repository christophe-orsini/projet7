package com.ocdev.biblio.apibiblio.services;

import com.ocdev.biblio.apibiblio.entities.Theme;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;

/**
 * Interface de déclaration des services pour les themes
 * @author C.Orsini
 */
public interface ThemeService
{
	/**
	 * Création d'un nouveau thème.
	 * @param theme Le thème à créer
	 * @return Le thème créé
	 * @throws AlreadyExistsException levée si le thème existe déjà
	 */
	public Theme creer(String theme) throws AlreadyExistsException;
	
	/**
	 * Permet d'obtenir un thème.
	 * @param id L'ID du thème
	 * @return Le thème correspondant à l'ID
	 * @throws EntityNotFoundException levée si le thème n'existe pas
	 */
	public Theme obtenir(Long id) throws EntityNotFoundException;
	/**
	 * Permet d'obtenir un thème.
	 * @param id Le nom du thème
	 * @return Le thème correspondant à l'ID
	 * @throws EntityNotFoundException levée si le thème n'existe pas
	 */
	public Theme obtenir(String nom) throws EntityNotFoundException;
}
