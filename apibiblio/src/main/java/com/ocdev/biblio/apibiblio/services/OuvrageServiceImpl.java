package com.ocdev.biblio.apibiblio.services;

import java.util.Collection;
import java.util.Optional;
import javax.transaction.Transactional;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ocdev.biblio.apibiblio.dao.OuvrageRepository;
import com.ocdev.biblio.apibiblio.dao.ThemeRepository;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.entities.Theme;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;
import com.ocdev.biblio.apibiblio.exceptions.RequiredAndNotFoundException;
import com.ocdev.biblio.apibiblio.utils.Tools;

/**
 * Classe d'implémentation des service pour les ouvrages
 * Cette classe utilise le repository {@link com.ocdev.biblio.apibiblio.dao.OuvrageRepository}
 * @author C.Orsini
 * @see com.ocdev.biblio.apibiblio.dao.OuvrageRepository
 */
@Service
public class OuvrageServiceImpl implements OuvrageService
{
	@Autowired private OuvrageRepository ouvrageRepository;
	@Autowired private ThemeRepository themeRepository;
	
	@Override
	@Transactional
	public Ouvrage ajouterOuChanger(String titre, String resume, String auteur, int anneePublication, String theme, int qte) throws NullOrEmptyArgumentException
	{
		if (Tools.stringIsNullOrEmpty(titre) || Tools.stringIsNullOrEmpty(auteur) || Tools.stringIsNullOrEmpty(theme)) throw new NullOrEmptyArgumentException();
		
		Theme themeObject = themeRepository.findByNom(theme);
		if (themeObject == null) themeObject = themeRepository.save(new Theme(theme));
		
		Ouvrage ouvrage = ouvrageRepository.findByTitreContainingIgnoreCase(titre).orElse(new Ouvrage(titre, auteur, anneePublication, themeObject));
		ouvrage.setResume(resume);		
		ouvrage.setNbreExemplaire(qte);		
		
		return ouvrageRepository.save(ouvrage);
	}

	@Override
	public Collection<Ouvrage> rechercherOuvrages(String critere)
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

	@Override
	public Ouvrage consulterOuvrage(Long id) throws RequiredAndNotFoundException
	{
		Optional<Ouvrage> ouvrage = ouvrageRepository.findById(id);
		if (!ouvrage.isPresent()) throw new RequiredAndNotFoundException("L'ouvrage avec l'ID " + id + " n'existe pas");
		
		return ouvrage.get();
	}
}
