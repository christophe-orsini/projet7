package com.ocdev.biblio.apibiblio.assemblers;

import org.springframework.stereotype.Component;
import com.ocdev.biblio.apibiblio.dto.UtilisateurDto;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;

@Component
public class UtilisateurDtoConverter implements IDtoConverter<Utilisateur, UtilisateurDto>
{
	@Override
	public UtilisateurDto convertEntityToDto(Utilisateur utilisateur)
	{
		UtilisateurDto utilisateurDto = new UtilisateurDto();
		
		utilisateurDto.setId(utilisateur.getId());
		utilisateurDto.setEmail(utilisateur.getEmail());
		utilisateurDto.setPassword(utilisateur.getPassword());
		utilisateurDto.setNom(utilisateur.getNom());
		utilisateurDto.setPrenom(utilisateur.getPrenom());
		
		return utilisateurDto;
	}

	@Override
	public Utilisateur convertDtoToEntity(UtilisateurDto utilisateurDto)
	{
		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setId(utilisateurDto.getId());
		utilisateur.setEmail(utilisateurDto.getEmail());
		utilisateur.setPassword(utilisateurDto.getPassword());
		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setPrenom(utilisateurDto.getPrenom());
		
		return utilisateur;
	}
}
