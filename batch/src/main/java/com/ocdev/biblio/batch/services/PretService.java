package com.ocdev.biblio.batch.services;

import java.util.Collection;
import java.util.Date;
import com.ocdev.biblio.batch.objects.Pret;


public interface PretService
{
	public Collection<Pret> listePretsEnRetard(Date dateRetard);
}
