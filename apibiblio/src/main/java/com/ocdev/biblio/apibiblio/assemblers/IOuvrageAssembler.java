package com.ocdev.biblio.apibiblio.assemblers;

import com.ocdev.biblio.apibiblio.dto.OuvrageDetail;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;

public interface IOuvrageAssembler
{
	/**
	 * Méthode de mappage d'un {@link com.ocdev.biblio.apibiblio.entities.Ouvrage} vers un DTO ouvrage.
	 * @param ouvrage
	 * @return Le DTO {@link com.ocdev.biblio.apibiblio.dto.OuvrageDetail} de l'ouvrage
	 * @throws NullArgumentAssemblerException levée si l'argument est null
	 */
	OuvrageDetail createOuvrageDetail(Ouvrage ouvrage) throws NullOrEmptyArgumentException;
	/**
	 * Méthode de mappage d'un DTO {@link com.ocdev.biblio.apibiblio.dto.OuvrageDetail} vers un ouvrage.
	 * @param ouvrageDetail
	 * @return L'{@link com.ocdev.biblio.apibiblio.entities.Ouvrage}
	 */
	Ouvrage createOuvrage(OuvrageDetail ouvrageDetail);
	/**
	 * Méthode de mise à jour d'un ouvrage à partir d'un DTO {@link com.ocdev.biblio.apibiblio.dto.OuvrageDetail}.
	 * @param ouvrageDetail
	 * @return L'{@link com.ocdev.biblio.apibiblio.entities.Ouvrage}
	 */
	Ouvrage updateOuvrage(OuvrageDetail ouvrageDetail);
}
