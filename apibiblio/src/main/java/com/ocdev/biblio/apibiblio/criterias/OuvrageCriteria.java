package com.ocdev.biblio.apibiblio.criterias;

public class OuvrageCriteria
{
	private Long id;
	private String titre;
	private String auteur;
	private int anneeEdition;
	private String theme;
	private int nbreExemplaire;
	public OuvrageCriteria(Long id, String titre, String auteur, int anneeEdition, String theme, int nbreExemplaire)
	{
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.anneeEdition = anneeEdition;
		this.theme = theme;
		this.nbreExemplaire = nbreExemplaire;
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
	public String getTheme()
	{
		return theme;
	}
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
	public int getNbreExemplaire()
	{
		return nbreExemplaire;
	}
	public void setNbreExemplaire(int nbreExemplaire)
	{
		this.nbreExemplaire = nbreExemplaire;
	}
}
