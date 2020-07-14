package com.ocdev.biblio.apibiblio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "OuvrageDto", description = "Modèle DTO pour la classe Ouvrage")
public class OuvrageDto implements Serializable
{
	@ApiModelProperty(position = 1, required = false, value = "ID de l'ouvrage attribuée par le système", allowEmptyValue = true)
	private Long id;
	@ApiModelProperty(position = 2, required = true, value = "Titre de l'ouvrage")
	@NotBlank(message="Le titre est obligatoire")
	private String titre; // TODO ID
	@ApiModelProperty(position = 3, required = false, value = "Résumé de l'ouvrage")
	private String resume;
	@ApiModelProperty(position = 4, required = true, value = "Auteur de l'ouvrage")
	@NotBlank(message="L'auteur est obligatoire")
	private String auteur;
	@ApiModelProperty(position = 5, required = true, value = "Année de parution de l'ouvrage")
	@Positive(message="L'année de parution est obligatoire")
	private int anneeEdition;
	@ApiModelProperty(position = 6, required = false, value = "Nombre d'exemplaires disponibles pour prêt")
	@PositiveOrZero(message = "Le nombre d'exemplaires ne peut pas être négatif")
	private int nbreExemplaire;
	@ApiModelProperty(position = 7, required = true, value = "Thème (catégorie) de l'ouvrage")
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
