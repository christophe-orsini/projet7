package com.ocdev.biblio.apibiblio.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe modèle pour un thème
 * @author C.Orsini
 *
 */
@Entity
public class Theme implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nom;

	//@OneToMany(mappedBy = "theme", fetch = FetchType.LAZY)
	//@JsonIgnore
	//private Collection<Ouvrage> ouvrages;

	public Theme()
	{
		super();
	}

	public Theme(String nom)
	{
		super();
		this.nom = nom;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

//		public Collection<Ouvrage> getOuvrages()
//	{
//		return ouvrages;
//	}
//
//		public void setOuvrages(Collection<Ouvrage> ouvrages)
//	{
//		this.ouvrages = ouvrages;
//	}

	@Override
	public String toString()
	{
		return nom;
	}
}
