package com.ocdev.biblio.apibiblio.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée si une prolongation de pêt est impossible.
 * Levé lorsque une prolongation de prêt depasse la durée maximale.
 * @author C.Orsini
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DelayLoanException extends BiblioException
{
	public DelayLoanException(String message)
	{
		super(message);
	}
}
