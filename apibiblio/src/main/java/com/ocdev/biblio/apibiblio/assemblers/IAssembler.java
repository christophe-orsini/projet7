package com.ocdev.biblio.apibiblio.assemblers;

import com.ocdev.biblio.apibiblio.exceptions.NullOrEmptyArgumentException;

public interface IAssembler<T, U>
{
	/**
	 * Méthode de mappage d'une entité vers un DTO.
	 * @param entity L'entité à mapper
	 * @return Le DTO
	 * @throws NullOrEmptyArgumentException levée si l'argument est null
	 */
	U createDto(T entity) throws NullOrEmptyArgumentException;
	/**
	 * Méthode de mappage d'un DTO vers une entité.
	 * @param dto Le DTO à mapper
	 * @return L'entité
	 */
	T createEntity(U dto);
	/**
	 * Méthode de mise à jour d'une entité à partir d'un DTO.
	 * @param dto Le DTO à mapper
	 * @return L'entité
	 */
	T updateEntity(U dto);
}
