package com.ocdev.biblio.apibiblio.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Classe modèle pour un ouvrage
 * @author C.Orsini
 *
 */
@Entity
public class Ouvrage implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titre;

	@Column(nullable = true)
	private String resume;

	@Column(nullable = false)
	private String auteur;

	@Column(nullable = false)
	private int anneeParution;

	@Column(nullable = true)
	private int nbreExemplaire;

	@ManyToOne
	@JoinColumn(name = "theme_id")
	//@JsonManagedReference
	private Theme theme;

	@OneToMany(mappedBy = "ouvrage", fetch = FetchType.EAGER)
	private Collection<Pret> prets;

	public Ouvrage() {}

	public Ouvrage(String titre, String auteur, int annee, Theme theme)
	{
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.anneeParution = annee;
		this.theme = theme;
	}

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

	public Theme getTheme()
	{
		return theme;
	}

	public void setTheme(Theme theme)
	{
		this.theme = theme;
	}

	public Collection<Pret> getPrets()
	{
		return prets;
	}

	public void setPrets(Collection<Pret> prets)
	{
		this.prets = prets;
	}

	@Override
	public String toString()
	{
		return "Ouvrage [titre=" + titre + ", auteur=" + auteur + "]";
	}
}
