package com.ocdev.biblio.apibiblio.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée si une entité existe déjà.
 * Levé lorsque une entité existe déjà et que l'on ne peut pas eb créer une autre.
 * @author C.Orsini
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends BiblioException
{
	private final String fieldName;
	
	public AlreadyExistsException(String fieldName, String message)
	{
		super(message);
		this.fieldName = fieldName;
	}

	public String getFieldName()
	{
		return fieldName;
	}
}
