package com.ocdev.biblio.apibiblio.errors;

/**
 * Classe exception pour argument null ou vide.
 * Levé lorsque l'argument d'une méthode est null ou vide.
 * @author C.Orsini
 */
public class NullOrEmptyArgumentException extends BiblioException
{
	public NullOrEmptyArgumentException(String message)
	{
		super(message);
	}
}
