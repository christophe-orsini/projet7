package com.ocdev.biblio.apibiblio.services;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import com.ocdev.biblio.apibiblio.entities.Theme;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;

@Service
public class ThemeServiceImpl implements ThemeService
{

	@Override
	public Theme creer(String theme) throws AlreadyExistsException
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

	@Override
	public Theme obtenir(Long id) throws EntityNotFoundException
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

	@Override
	public Theme obtenir(String nom) throws EntityNotFoundException
	{
		// TODO Auto-generated method stub
		throw new NotYetImplementedException();
	}

}
