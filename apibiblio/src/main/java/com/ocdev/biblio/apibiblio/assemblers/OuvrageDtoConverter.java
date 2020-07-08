package com.ocdev.biblio.apibiblio.assemblers;

import org.springframework.stereotype.Component;
import com.ocdev.biblio.apibiblio.dto.OuvrageDto;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.entities.Theme;

@Component
public class OuvrageDtoConverter implements IDtoConverter<Ouvrage, OuvrageDto>
{
	@Override
	public OuvrageDto convertEntityToDto(Ouvrage ouvrage)
	{
		OuvrageDto ouvrageDto = new OuvrageDto();
		
		ouvrageDto.setId(ouvrage.getId());
		ouvrageDto.setTitre(ouvrage.getTitre());
		ouvrageDto.setAuteur(ouvrage.getAuteur());
		ouvrageDto.setResume(ouvrage.getResume());
		ouvrageDto.setAnneeEdition(ouvrage.getAnneeEdition());
		ouvrageDto.setTheme(ouvrage.getTheme().toString());
		ouvrageDto.setNbreExemplaire(ouvrage.getNbreExemplaire());
		
		return ouvrageDto;
	}

	@Override
	public Ouvrage convertDtoToEntity(OuvrageDto ouvrageDto)
	{
		Ouvrage ouvrage = new Ouvrage();
		
		ouvrage.setId(ouvrageDto.getId());
		ouvrage.setTitre(ouvrageDto.getTitre());
		ouvrage.setAuteur(ouvrageDto.getAuteur());
		ouvrage.setResume(ouvrageDto.getResume());
		ouvrage.setAnneeEdition(ouvrageDto.getAnneeEdition());
		ouvrage.setTheme(new Theme(ouvrageDto.getTheme()));
		ouvrage.setNbreExemplaire(ouvrageDto.getNbreExemplaire());
		
		return ouvrage;
	}
}
