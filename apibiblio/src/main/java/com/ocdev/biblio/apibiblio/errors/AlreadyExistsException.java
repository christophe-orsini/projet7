package com.ocdev.biblio.apibiblio.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée si une entité existe déjà.
 * Levé lorsque une entité existe déjà et que l'on ne peut pas en créer une autre.
 * @author C.Orsini
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends BiblioException
{
	
	public AlreadyExistsException(String message)
	{
		super(message);
	}
}
