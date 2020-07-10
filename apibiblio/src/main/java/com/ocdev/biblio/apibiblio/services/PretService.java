package com.ocdev.biblio.apibiblio.services;

import java.util.Collection;
import com.ocdev.biblio.apibiblio.dto.PretDto;
import com.ocdev.biblio.apibiblio.errors.DelayLoanException;
import com.ocdev.biblio.apibiblio.errors.DuplicateLoanException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.errors.NotEnoughCopiesException;

public interface PretService
{
	public PretDto creer(PretDto pretDto) throws DuplicateLoanException, EntityNotFoundException, NotEnoughCopiesException;
	public PretDto retournerOuvrage(Long pretId) throws EntityNotFoundException;
	
	public PretDto prolonger(Long pretId) throws EntityNotFoundException, DelayLoanException;
	public Collection<PretDto> listerSesPrets(Long abonneId) throws EntityNotFoundException;
	public PretDto consulter(Long pretId) throws EntityNotFoundException;
}
