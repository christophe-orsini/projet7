package com.ocdev.biblio.batch.services;

import java.util.Collection;
import java.util.Date;
import com.ocdev.biblio.batch.model.Pret;

public interface PretService
{
	/**
	 * Retourne une liste de prtes en retard
	 * @param dateRetard Date en dessous de laquelle on considère que le pret est en retard
	 * @return La liste des prets
	 */
	public Collection<Pret> listePretsEnRetard(Date dateRetard);
}
