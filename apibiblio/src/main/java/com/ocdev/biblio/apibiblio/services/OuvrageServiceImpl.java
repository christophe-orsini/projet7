package com.ocdev.biblio.apibiblio.services;

import java.util.Collection;
import java.util.Optional;
import javax.transaction.Transactional;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ocdev.biblio.apibiblio.assemblers.IDtoConverter;
import com.ocdev.biblio.apibiblio.criterias.OuvrageCriteria;
import com.ocdev.biblio.apibiblio.dao.OuvrageRepository;
import com.ocdev.biblio.apibiblio.dto.OuvrageCreateDto;
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
	@Autowired private IDtoConverter<Ouvrage, OuvrageCreateDto> ouvrageConverter;
	
	@Override
	@Transactional
	public Ouvrage creer(OuvrageCreateDto ouvrageCreateDto) throws AlreadyExistsException
	{
		Optional<Ouvrage> ouvrageExists = ouvrageRepository.findByTitreIgnoreCase(ouvrageCreateDto.getTitre());
		if (ouvrageExists.isPresent())
		{
			// un ouvrage avec ce titre existe déjà
			// log
			throw new AlreadyExistsException("Un ouvrage avec le même titre existe déjà");
		}
		
		Ouvrage ouvrage = ouvrageConverter.convertDtoToEntity(ouvrageCreateDto);
		
		// log
		return ouvrageRepository.save(ouvrage);
	}

	@Override
	public Collection<Ouvrage> rechercherOuvrages(OuvrageCriteria critere)
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

	@Override
	public Ouvrage consulterOuvrage(Long id) throws EntityNotFoundException
	{
		Optional<Ouvrage> ouvrage = ouvrageRepository.findById(id);
		if (!ouvrage.isPresent()) throw new EntityNotFoundException("L'ouvrage n'existe pas");
		
		return ouvrage.get();
	}
}
