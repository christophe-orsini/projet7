package com.ocdev.biblio.apibiblio.entities;

/**
* Enum pour le statut d'un prêt.
* @author C.Orsini
*
*/
public enum Statut
{
	RESERVE ("Réservé"),
	EN_COURS ("En cours"),
	PROLONGE ("Prolongé"),
	RETARD ("En retard"),
	RETOURNE ("Retourné");
	
	private String libelle;
	
	Statut(String libelle)
	{
		this.libelle = libelle;
	}

	public String getLibelle()
	{
		return libelle;
	}

	public void setLibelle(String libelle)
	{
		this.libelle = libelle;
	}
}
