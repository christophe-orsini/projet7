package com.ocdev.biblio.apibiblio.services;

import java.util.Collection;
import com.ocdev.biblio.apibiblio.entities.Pret;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.DelayLoanException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.errors.NotEnoughCopiesException;

/**
 * Interface de déclaration des services pour les ouvrages
 * @author C.Orsini
 */
public interface PretService
{
	/**
	 * Création d'un prêt pour un ouvrage et un abonné.
	 * @param abonneId L'ID de l'abonné
	 * @param ouvrageId L'ID de l'ouvrage
	 * @return Le prêt créé
	 * @throws AlreadyExistsException levée si un prêt en cours existe déjà pour cet ouvrage et cet abonné
	 * @throws EntityNotFoundException levée si l'ouvrage ou l'abonné n'existent pas
	 * @throws NotEnoughCopiesException levée s'il n'y a pas assez d'exemplaires pour preter l'ouvrage
	 */
	public Pret creer(Long abonneId, Long ouvrageId) throws AlreadyExistsException, EntityNotFoundException, NotEnoughCopiesException;
	/**
	 * Retour d'un prêt
	 * @param pretId L'ID du prêt
	 * @throws EntityNotFoundException levée si le prêt n'existe pas
	 */
	public void retournerOuvrage(Long pretId) throws EntityNotFoundException;
	/**
	 * Permet de prolonger un prêt
	 * @param pretId L'ID du prêt
	 * @return Le prêt modifié
	 * @throws EntityNotFoundException levée si le prêt n'existe pas
	 * @throws DelayLoanException levée si le prêt ne peut plus être prolongé
	 */
	public Pret prolonger(Long pretId) throws EntityNotFoundException, DelayLoanException;
	/**
	 * Retoune la liste des prêts d'un abonné
	 * @param abonneId L'ID de l'abonné
	 * @return La liste des prêt qui peut être vide
	 * @throws EntityNotFoundException levée si l'abonné n'existe pas
	 */
	public Collection<Pret> listerSesPrets(Long abonneId) throws EntityNotFoundException;
	/**
	 * Permet de consulter un prêt
	 * @param pretId L'ID du prêt
	 * @return Le prêt
	 * @throws EntityNotFoundException levée si le prêt n'existe pas
	 */
	public Pret consulter(Long pretId) throws EntityNotFoundException;
}
