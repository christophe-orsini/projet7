package com.ocdev.biblio.apibiblio.errors;

/**
 * Exception levée si une entité existe déjà.
 * Levé lorsque une entité existe déjà et que l'on ne peut pas eb créer une autre.
 * @author C.Orsini
 */
public class AlreadyExistsException extends BiblioException
{
	public AlreadyExistsException(String message)
	{
		super(message);
	}
}
