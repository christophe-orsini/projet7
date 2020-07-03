package com.ocdev.biblio.apibiblio.assemblers;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;
import com.ocdev.biblio.apibiblio.dto.UtilisateurDetail;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;

@Component
public class UtilisateurAssembler implements IAssembler<Utilisateur, UtilisateurDetail>
{
	@Override
	public UtilisateurDetail createDto(Utilisateur utilisateur) throws NullOrEmptyArgumentException
	{
		if (utilisateur == null) throw new NullOrEmptyArgumentException("L'utilisateur ne peut pas être null");
		
		UtilisateurDetail utilisateurDetail = new UtilisateurDetail();
		utilisateurDetail.setEmail(utilisateur.getEmail());
		utilisateurDetail.setPassword(utilisateur.getPassword());
		utilisateurDetail.setNom(utilisateur.getNom());
		utilisateurDetail.setPrenom(utilisateur.getPrenom());
		
		return utilisateurDetail;
	}

	@Override
	public Utilisateur createEntity(UtilisateurDetail dto)
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

	@Override
	public Utilisateur updateEntity(UtilisateurDetail dto)
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

}
