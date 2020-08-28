package com.ocdev.biblio.batch.services;

import java.util.Collection;

import javax.mail.MessagingException;

import com.ocdev.biblio.batch.model.Pret;
import com.ocdev.biblio.batch.model.Utilisateur;

public interface EmailService
{
	/**
	 * Envoi d'un email pour retour d'emprunt en retard
	 * @param abonne L'abonné destinataire de l'email
	 * @throws MessagingException
	 */
	public void envoiEmailEnRetard(Utilisateur abonne) throws MessagingException;
	
	/**
	 * Liste les prets par abonné
	 * @param prets La liste des prets
	 * @return La liste des abonnés avec leurs prets respectifs
	 */
	public Collection<Utilisateur> pretsParAbonne(Collection<Pret> prets);
}
