package com.ocdev.biblio.apibiblio.services;

import java.util.Collection;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;
import com.ocdev.biblio.apibiblio.exceptions.RequiredAndNotFoundException;

/**
 * Interface de déclaration des services pour les ouvrages
 * @author C.Orsini
 */
public interface OuvrageService
{
	/**
	 * Méthode permettant d'ajouter ou de mettre à jour un ouvrage.
	 * @param titre Le titre de l'ouvrage
	 * @param resume Le résumé de l'ouvrage
	 * @param auteur L'auteur de l'ouvrage
	 * @param anneePublication L'année de publication
	 * @param theme Le {@link com.ocdev.biblio.apibiblio.entities.Theme} de l'ouvrage
	 * @param qte Nombre d'exemplaires disponibles
	 * @return L'ouvrage nouvellement créé ou mis à jour
	 * @throws {@link com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException} levée si un argument est null ou vide
	 */
	public Ouvrage ajouterOuChanger(String titre, String resume, String auteur, int anneePublication, String theme, int qte) throws NullOrEmptyArgumentException;
	/**
	 * Méthode permettant de rechercher des ouvrages en fonction de critères.
	 * @param critere Critère de recherche
	 * @return Une collection des ouvrages trouvés (peut être vide)
	 */
	public Collection<Ouvrage> rechercherOuvrages(String critere);
	/**
	 * Méthode permettant d'obtenir les détails d'un ouvrage.
	 * 
	 * @param id L'id de l'ouvrage
	 * @return L'ouvrage trouvé
	 * @throws {@link com.ocdev.biblio.apibiblio.exceptions.RequiredAndNotFoundException} levée si l'id n'existe pas
	 */
	public Ouvrage consulterOuvrage(Long id) throws RequiredAndNotFoundException;
}
