package com.ocdev.biblio.apibiblio.services;

import java.util.Date;
import java.util.List;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;

public interface BatchService
{
	public List<Ouvrage> listerRetards(Date dateLimite);

}
