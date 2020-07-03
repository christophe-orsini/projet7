package com.ocdev.biblio.apibiblio.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UtilisateurDetail
{
	@NotEmpty(message="L'email est obligatoire")
	@Email(message="L'adresse email n'est pas bien formée")
	private String email; // login
	@NotEmpty(message="Le mot de passe est obligatoire")
	private String password;
	@NotEmpty(message="Le nom est obligatoire")
	private String nom;
	private String prenom;
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
