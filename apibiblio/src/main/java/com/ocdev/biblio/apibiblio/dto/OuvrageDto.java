package com.ocdev.biblio.apibiblio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class OuvrageDto implements Serializable
{
	private Long id;
	@NotBlank(message="Le titre est obligatoire")
	private String titre;
	private String resume;
	@NotBlank(message="L'auteur est obligatoire")
	private String auteur;
	@NotBlank(message="L'année de parution est obligatoire")
	private int anneeEdition;
	@PositiveOrZero(message = "Le nombre d'exemplaires ne peut pas être négatif")
	private int nbreExemplaire;
	@NotBlank(message="Le thème est obligatoire")
	private String theme;

	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
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
	public int getAnneeEdition()
	{
		return anneeEdition;
	}
	public void setAnneeEdition(int anneeEdition)
	{
		this.anneeEdition = anneeEdition;
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
