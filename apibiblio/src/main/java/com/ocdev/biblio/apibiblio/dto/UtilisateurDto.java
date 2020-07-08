package com.ocdev.biblio.apibiblio.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UtilisateurDto implements Serializable
{
	private Long Id;
	@NotBlank(message="L'email est obligatoire")
	@Email(message="L'adresse email n'est pas bien formée")
	private String email; // login
	@NotBlank(message="Le mot de passe est obligatoire")
	@Size(min = 4, message = "Le mot de passe doit avoir au moins 4 caratères")
	private String password;
	@NotBlank(message="Le nom est obligatoire")
	private String nom;
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
