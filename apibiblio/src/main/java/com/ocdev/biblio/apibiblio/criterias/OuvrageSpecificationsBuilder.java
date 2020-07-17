package com.ocdev.biblio.apibiblio.criterias;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import com.ocdev.biblio.apibiblio.dao.ThemeRepository;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.entities.Theme;
import com.ocdev.biblio.apibiblio.utils.Tools;

public class OuvrageSpecificationsBuilder
{
	@Autowired 
	private ThemeRepository themeRepository;
	
	private final List<OuvrageSpecification> specs = new ArrayList<OuvrageSpecification>();

    public OuvrageSpecificationsBuilder(OuvrageCriteria critere)
    {
    	if (!Tools.stringIsNullOrEmpty(critere.getTitre()))
    	{
    		specs.add(new OuvrageSpecification(new SearchCriteria("titre", "==", critere.getTitre())));
    	}
    	if (!Tools.stringIsNullOrEmpty(critere.getAuteur()))
    	{
    		specs.add(new OuvrageSpecification(new SearchCriteria("auteur", "==", critere.getAuteur())));
    	}
    	if (critere.getAnneeEdition() > 0)
    	{
    		specs.add(new OuvrageSpecification(new SearchCriteria("anneeEdition", ">=", critere.getAnneeEdition())));
    	}
    	if (critere.getNbreExemplaire() > 0)
    	{
    		specs.add(new OuvrageSpecification(new SearchCriteria("nbreExemplaire", ">=", critere.getNbreExemplaire())));
    	}
//    	if (!Tools.stringIsNullOrEmpty(critere.getTheme()))
//    	{
//    		Optional<Theme> theme = themeRepository.findByNomIgnoreCase(critere.getTheme());
//    		if (theme.isPresent())
//    		{
//    			specs.add(new OuvrageSpecification(new SearchCriteria("theme", "==", theme.get())));
//    		}
//    	}
    }

    public Specification<Ouvrage> build()
    {
        if (specs.size() == 0) {
            return null;
        }

         Specification<Ouvrage> result = specs.get(0);

        for (int i = 1; i < specs.size(); i++)
        {
        	result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }

}
