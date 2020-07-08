package com.ocdev.biblio.apibiblio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ocdev.biblio.apibiblio.assemblers.IDtoConverter;
import com.ocdev.biblio.apibiblio.dao.UtilisateurRepository;
import com.ocdev.biblio.apibiblio.dto.UtilisateurDto;
import com.ocdev.biblio.apibiblio.entities.Role;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService
{
	@Autowired private UtilisateurRepository utilisateurRepository;
	@Autowired IDtoConverter<Utilisateur, UtilisateurDto> utilisateurConverter;
	
	@Override
	public UtilisateurDto creer(UtilisateurDto utilisateurDto) throws AlreadyExistsException
	{
		if (utilisateurRepository.findByEmailIgnoreCase(utilisateurDto.getEmail()).isPresent())
		{
			// un utilisateur avec cet email existe déjà
			// log
			throw new AlreadyExistsException("email", "Un utilisateur avec cet email existe déjà");
		}
		
		Utilisateur utilisateur = utilisateurConverter.convertDtoToEntity(utilisateurDto);
		utilisateur.setRole(Role.ROLE_ABONNE);
		
		// log
		utilisateur = utilisateurRepository.save(utilisateur);
		
		return utilisateurConverter.convertEntityToDto(utilisateur);
	}
}
