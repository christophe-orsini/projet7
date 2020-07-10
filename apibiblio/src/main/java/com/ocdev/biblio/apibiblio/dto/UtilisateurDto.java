package com.ocdev.biblio.apibiblio.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UtilisateurDto", description = "Modèle DTO pour la classe Utilisateur")
public class UtilisateurDto implements Serializable
{
	@ApiModelProperty(position = 1, required = false, value = "ID de l'utilisateur attribuée par le système", allowEmptyValue = true)
	private Long Id;
	@ApiModelProperty(position = 2, required = true, value = "Adresse email qui sert de login", example = "email@domain.tld")
	@NotBlank(message="L'email est obligatoire")
	@Email(message="L'adresse email n'est pas bien formée")
	private String email; // login
	@ApiModelProperty(position = 3, required = true, value = "Mot de passe avec au moins 4 caractères", example = "1234")
	@NotBlank(message="Le mot de passe est obligatoire")
	@Size(min = 4, message = "Le mot de passe doit avoir au moins 4 caratères")
	private String password;
	@ApiModelProperty(position = 4, required = true, value = "Nom de famille", example = "MonNom")
	@NotBlank(message="Le nom est obligatoire")
	private String nom;
	@ApiModelProperty(position = 5, required = false, value = "Prénom", example = "MonPrénom")
	private String prenom;
	
	public Long getId()
	{
		return Id;
	}
	public void setId(Long id)
	{
		Id = id;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getNom()
	{
		return nom;
	}
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	public String getPrenom()
	{
		return prenom;
	}
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}
}
