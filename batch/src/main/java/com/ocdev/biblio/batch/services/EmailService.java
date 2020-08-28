package com.ocdev.biblio.batch.services;

import java.util.Collection;

import javax.mail.MessagingException;

import com.ocdev.biblio.batch.model.Pret;
import com.ocdev.biblio.batch.model.Utilisateur;

public interface EmailService
{
	
	public void sendEmailEnRetard(Utilisateur abonne) throws MessagingException;
	
	/**
	 * Liste les prets par utilisateur
	 * @param prets La liste des prets
	 * @return La liste des utilisateurs
	 */
	public Collection<Utilisateur> pretsParAbonne(Collection<Pret> prets);
}
