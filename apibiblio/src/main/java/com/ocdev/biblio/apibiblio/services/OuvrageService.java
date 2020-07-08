package com.ocdev.biblio.apibiblio.services;

import java.util.Collection;
import com.ocdev.biblio.apibiblio.dto.OuvrageDto;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;

/**
 * Interface de déclaration des services pour les ouvrages
 * @author C.Orsini
 */
public interface OuvrageService
{
	/**
	 * Méthode permettant d'ajouter .
	 * @param ouvrageDto Le DTO de l'ouvrage à créer
	 * @return Le DTO de l'ouvrage créé
	 * @throws AlreadyExistsException levée si le titre existe déjà
	 */
	public OuvrageDto creer(OuvrageDto ouvrageDto) throws AlreadyExistsException;
	/**
	 * Méthode permettant de rechercher des ouvrages en fonction de critères.
	 * @param critere Critère de recherche
	 * @return Une collection des DTOs d'ouvrages trouvés (peut être vide)
	 */
	public Collection<OuvrageDto> rechercherOuvrages(String critere);
	/**
	 * Méthode permettant d'obtenir les détails d'un ouvrage.
	 * 
	 * @param id L'id de l'ouvrage
	 * @return Le DTO de l'ouvrage trouvé
	 * @throws EntityNotFoundException levée si l'id n'existe pas
	 */
	public OuvrageDto consulterOuvrage(Long id) throws EntityNotFoundException;
}
