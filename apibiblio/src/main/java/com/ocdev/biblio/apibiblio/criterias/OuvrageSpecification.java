package com.ocdev.biblio.apibiblio.criterias;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import com.ocdev.biblio.apibiblio.dao.ThemeRepository;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.entities.Theme;
import com.ocdev.biblio.apibiblio.utils.Tools;

public class OuvrageSpecification
{
	public static Specification<Ouvrage> build(OuvrageCriteria critere, ThemeRepository themeRepository)
    {
		List<Specification<Ouvrage>> specsAnd = new ArrayList<Specification<Ouvrage>>();
		List<Specification<Ouvrage>> specsOr = new ArrayList<Specification<Ouvrage>>();
		
		if (!Tools.stringIsNullOrEmpty(critere.getTitre()))
		{
			specsAnd.add((ouvrage, cq, cb) -> cb.like(ouvrage.get("titre"), "%" + critere.getTitre() + "%"));
		}
		if (!Tools.stringIsNullOrEmpty(critere.getAuteur()))
		{
			specsAnd.add((ouvrage, cq, cb) -> cb.like(ouvrage.get("auteur"), "%" + critere.getAuteur() + "%"));
		}
		if (critere.getAnneeEdition() > 0)
		{
			specsAnd.add((ouvrage, cq, cb) -> cb.ge(ouvrage.get("anneeEdition"), critere.getAnneeEdition()));
		}
		if (critere.getNbreExemplaire() > 0)
		{
			specsAnd.add((ouvrage, cq, cb) -> cb.ge(ouvrage.get("nbreExemplaire"), critere.getNbreExemplaire()));
		}
		if (!Tools.stringIsNullOrEmpty(critere.getTheme()))
		{
			Collection<Theme> themes = themeRepository.findAllByNomContainsIgnoreCase(critere.getTheme());
			if (themes.size() > 0)
			{
				for (Theme theme : themes)
				{
					specsOr.add((ouvrage, cq, cb) -> cb.equal(ouvrage.get("theme"), theme));
				}
			}
		}
		
        if (specsAnd.size() == 0 && specsOr.size() == 0) return null;

        Specification<Ouvrage> result = null;

        if (specsAnd.size() > 0)
        {
        	result = specsAnd.get(0);
        	for (int i = 1; i < specsAnd.size(); i++)
            {
            	result = Specification.where(result).and(specsAnd.get(i));
            }
        }
        if (specsOr.size() > 0)
        {
        	result = specsOr.get(0);
        	for (int i = 1; i < specsOr.size(); i++)
            {
        		result = Specification.where(result).or(specsOr.get(i));
            }   	
        }
        return result;
    }
}
