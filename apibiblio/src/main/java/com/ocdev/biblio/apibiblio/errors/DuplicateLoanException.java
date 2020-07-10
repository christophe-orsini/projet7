package com.ocdev.biblio.apibiblio.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée si un prêt en cours existe déjà.
 * Levé lorsque un prêt en cours existe déjà et que l'on ne peut pas en créer une autre.
 * @author C.Orsini
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateLoanException extends BiblioException
{
	public DuplicateLoanException(String message)
	{
		super(message);
	}
}
