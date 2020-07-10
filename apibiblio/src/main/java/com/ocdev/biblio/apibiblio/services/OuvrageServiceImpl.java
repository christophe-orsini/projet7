package com.ocdev.biblio.apibiblio.services;

import java.util.Collection;
import java.util.Optional;
import javax.transaction.Transactional;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ocdev.biblio.apibiblio.assemblers.IDtoConverter;
import com.ocdev.biblio.apibiblio.dao.OuvrageRepository;
import com.ocdev.biblio.apibiblio.dao.ThemeRepository;
import com.ocdev.biblio.apibiblio.dto.OuvrageDto;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;

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
	@Autowired private IDtoConverter<Ouvrage, OuvrageDto> ouvrageConverter;
	
	@Override
	@Transactional
	public OuvrageDto creer(OuvrageDto ouvrageDto) throws AlreadyExistsException
	{
		if (ouvrageRepository.findByTitreIgnoreCase(ouvrageDto.getTitre()).isPresent())
		{
			// un ouvrage avec ce titre existe déjà
			// log
			throw new AlreadyExistsException("Un ouvrage avec le titre '" + ouvrageDto.getTitre() + "' existe déjà");
		}
		
		Ouvrage ouvrage = ouvrageConverter.convertDtoToEntity(ouvrageDto);
		
		if (!themeRepository.findByNomIgnoreCase(ouvrage.getTheme().getNom()).isPresent())
		{
			// log
			themeRepository.save(ouvrage.getTheme());
		}
		
		// log
		ouvrage = ouvrageRepository.save(ouvrage);
		
		return ouvrageConverter.convertEntityToDto(ouvrage);
	}

	@Override
	public Collection<OuvrageDto> rechercherOuvrages(String critere)
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

	@Override
	public OuvrageDto consulterOuvrage(Long id) throws EntityNotFoundException
	{
		Optional<Ouvrage> ouvrage = ouvrageRepository.findById(id);
		if (!ouvrage.isPresent()) throw new EntityNotFoundException("L'ouvrage avec l'ID " + id + " n'existe pas");
		
		return ouvrageConverter.convertEntityToDto(ouvrage.get());
	}
}
