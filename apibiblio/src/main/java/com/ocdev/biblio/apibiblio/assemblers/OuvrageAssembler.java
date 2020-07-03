package com.ocdev.biblio.apibiblio.assemblers;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;
import com.ocdev.biblio.apibiblio.dto.OuvrageDetail;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;

@Component
public class OuvrageAssembler implements IOuvrageAssembler
{
	@Override
	public OuvrageDetail createOuvrageDetail(Ouvrage ouvrage) throws NullOrEmptyArgumentException
	{
		if (ouvrage == null) throw new NullOrEmptyArgumentException("L'ouvrage ne peut pas être null");
		
		OuvrageDetail ouvrageDetail = new OuvrageDetail();
		ouvrageDetail.setTitre(ouvrage.getTitre());
		ouvrageDetail.setAuteur(ouvrage.getAuteur());
		ouvrageDetail.setResume(ouvrage.getResume());
		ouvrageDetail.setAnneeParution(ouvrage.getAnneeParution());
		ouvrageDetail.setTheme(ouvrage.getTheme().toString());
		ouvrageDetail.setNbreExemplaire(ouvrage.getNbreExemplaire());
		
		return ouvrageDetail;
	}

	@Override
	public Ouvrage createOuvrage(OuvrageDetail ouvrageDetail)
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

	@Override
	public Ouvrage updateOuvrage(OuvrageDetail ouvrageDetail)
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

}
