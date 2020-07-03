package com.ocdev.biblio.apibiblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocdev.biblio.apibiblio.dao.UtilisateurRepository;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;
import com.ocdev.biblio.apibiblio.utils.Tools;

@Service
public class UtilisateurServiceImpl implements UtilisateurService
{
	@Autowired private UtilisateurRepository utilisateurRepository;

	@Override
	public Utilisateur inscrire(String email, String password, String nom, String prenom) throws NullOrEmptyArgumentException
	{
		if (Tools.stringIsNullOrEmpty(email) || Tools.stringIsNullOrEmpty(password) || Tools.stringIsNullOrEmpty(nom)) throw new NullOrEmptyArgumentException();
		
		Utilisateur utilisateur = utilisateurRepository.findByEmailIgnoreCase(email).orElse(new Utilisateur(email, password, nom, prenom));
		
		return utilisateurRepository.save(utilisateur);
	}

}
