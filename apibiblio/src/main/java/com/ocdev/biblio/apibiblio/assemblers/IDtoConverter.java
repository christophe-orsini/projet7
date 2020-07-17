package com.ocdev.biblio.apibiblio.assemblers;

public interface IDtoConverter<T, U>
{
	/**
	 * Méthode de mappage d'un DTO vers une entité.
	 * @param dto Le DTO à mapper
	 * @return L'entité
	 */
	T convertDtoToEntity(U dto);
}
