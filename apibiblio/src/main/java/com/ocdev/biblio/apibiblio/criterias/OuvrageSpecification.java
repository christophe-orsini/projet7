package com.ocdev.biblio.apibiblio.criterias;

import java.util.ArrayList;
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
		List<Specification<Ouvrage>> specs = new ArrayList<Specification<Ouvrage>>();
		
		if (!Tools.stringIsNullOrEmpty(critere.getTitre()))
		{
			specs.add((ouvrage, cq, cb) -> cb.like(ouvrage.get("titre"), "%" + critere.getTitre() + "%"));
		}
		if (!Tools.stringIsNullOrEmpty(critere.getAuteur()))
		{
			specs.add((ouvrage, cq, cb) -> cb.like(ouvrage.get("auteur"), "%" + critere.getAuteur() + "%"));
		}
		if (critere.getAnneeEdition() > 0)
		{
			specs.add((ouvrage, cq, cb) -> cb.ge(ouvrage.get("anneeEdition"), critere.getAnneeEdition()));
		}
		if (critere.getNbreExemplaire() > 0)
		{
			specs.add((ouvrage, cq, cb) -> cb.ge(ouvrage.get("nbreExemplaire"), critere.getNbreExemplaire()));
		}
		if (!Tools.stringIsNullOrEmpty(critere.getTheme()))
		{
			Optional<Theme> theme = themeRepository.findByNomIgnoreCase(critere.getTheme());
			if (theme.isPresent())
			{
				specs.add((ouvrage, cq, cb) -> cb.equal(ouvrage.get("theme"), theme.get()));
			}
		}
		
        if (specs.size() == 0) return null;

        Specification<Ouvrage> result = specs.get(0);

        for (int i = 1; i < specs.size(); i++)
        {
        	result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
