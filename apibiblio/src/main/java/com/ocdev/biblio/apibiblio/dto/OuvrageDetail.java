package com.ocdev.biblio.apibiblio.dto;

import java.io.Serializable;

public class OuvrageDetail implements Serializable
{
	private String titre;

	private String resume;

	private String auteur;

	private int anneeParution;

	private int nbreExemplaire;

	private String theme;

	public String getTitre()
	{
		return titre;
	}

	public void setTitre(String titre)
	{
		this.titre = titre;
	}

	public String getResume()
	{
		return resume;
	}

	public void setResume(String resume)
	{
		this.resume = resume;
	}

	public String getAuteur()
	{
		return auteur;
	}

	public void setAuteur(String auteur)
	{
		this.auteur = auteur;
	}

	public int getAnneeParution()
	{
		return anneeParution;
	}

	public void setAnneeParution(int anneeParution)
	{
		this.anneeParution = anneeParution;
	}

	public int getNbreExemplaire()
	{
		return nbreExemplaire;
	}

	public void setNbreExemplaire(int nbreExemplaire)
	{
		this.nbreExemplaire = nbreExemplaire;
	}

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}
}
