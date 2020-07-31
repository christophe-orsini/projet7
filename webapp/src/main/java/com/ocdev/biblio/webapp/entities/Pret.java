package com.ocdev.biblio.webapp.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe modèle pour un prêt
 * @author C.Orsini
 *
 */
public class Pret implements Serializable
{
	private Long id;
	private Date dateDebut;
	private Date dateFinPrevu;
	private Date dateRetour;
	private Statut statut;
	private int nbreProlongations;
	private Utilisateur abonne;
	private Ouvrage ouvrage;

	public Pret()
	{
		super();
	}

	public Pret(Utilisateur abonne, Ouvrage ouvrage)
	{
		super();
		this.abonne = abonne;
		this.ouvrage = ouvrage;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getDateDebut()
	{
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut)
	{
		this.dateDebut = dateDebut;
	}

	public Date getDateFinPrevu()
	{
		return dateFinPrevu;
	}

	public void setDateFinPrevu(Date dateFinPrevu)
	{
		this.dateFinPrevu = dateFinPrevu;
	}

	public Date getDateRetour()
	{
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour)
	{
		this.dateRetour = dateRetour;
	}

	public Statut getStatut()
	{
		return statut;
	}

	public void setStatut(Statut statut)
	{
		this.statut = statut;
	}

	public int getNbreProlongations()
	{
		return nbreProlongations;
	}

	public void setNbreProlongations(int nbreProlongations)
	{
		this.nbreProlongations = nbreProlongations;
	}

	public Utilisateur getAbonne()
	{
		return abonne;
	}

	public void setAbonne(Utilisateur abonne)
	{
		this.abonne = abonne;
	}

	public Ouvrage getOuvrage()
	{
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage)
	{
		this.ouvrage = ouvrage;
	}

	@Override
	public String toString()
	{
		return "Pret [dateDebut=" + dateDebut + ", dateFinPrevu=" + dateFinPrevu + ", statut=" + statut + ", abonne="
				+ abonne + ", ouvrage=" + ouvrage + "]";
	}

}
