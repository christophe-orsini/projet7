package com.ocdev.biblio.apibiblio.assemblers;

import org.springframework.stereotype.Component;
import com.ocdev.biblio.apibiblio.dto.ThemeCreateDto;
import com.ocdev.biblio.apibiblio.entities.Theme;

@Component
public class ThemeCreateDtoConverter implements IDtoConverter<Theme, ThemeCreateDto>
{
	@Override
	public Theme convertDtoToEntity(ThemeCreateDto themeDto)
	{
		Theme theme = new Theme();
		
		theme.setNom(themeDto.getNom());
		
		return theme;
	}
}