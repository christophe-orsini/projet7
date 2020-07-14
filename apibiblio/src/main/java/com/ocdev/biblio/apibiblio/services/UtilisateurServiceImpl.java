package com.ocdev.biblio.apibiblio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ocdev.biblio.apibiblio.assemblers.IDtoConverter;
import com.ocdev.biblio.apibiblio.dao.UtilisateurRepository;
import com.ocdev.biblio.apibiblio.dto.UtilisateurCreateDto;
import com.ocdev.biblio.apibiblio.entities.Role;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService
{
	@Autowired private UtilisateurRepository utilisateurRepository;
	@Autowired IDtoConverter<Utilisateur, UtilisateurCreateDto> utilisateurConverter;
	
	@Override
	public Utilisateur creer(UtilisateurCreateDto utilisateurDto) throws AlreadyExistsException
	{
		Optional<Utilisateur> utilisateurExists = utilisateurRepository.findByEmailIgnoreCase(utilisateurDto.getEmail());
		if (utilisateurExists.isPresent())
		{
			// un utilisateur avec cet email existe déjà
			// log
			throw new AlreadyExistsException("Un utilisateur avec cet email existe déjà");
		}
		
		Utilisateur utilisateur = utilisateurConverter.convertDtoToEntity(utilisateurDto);
		utilisateur.setRole(Role.ROLE_ABONNE);
		
		// log
		return utilisateurRepository.save(utilisateur);
	}
}
