package com.ocdev.biblio.apibiblio.exceptions;

/**
 * Classe exception pour argument null ou vide.
 * Levé lorsque l'argument d'une méthode est null ou vide.
 * @author C.Orsini
 * @See com.ocdev.biblio.apibiblio.assemblers
 */
public class NullOrEmptyArgumentException extends BiblioException
{

	public NullOrEmptyArgumentException()
	{
		super();
	}

	public NullOrEmptyArgumentException(String message)
	{
		super(message);
	}
	
	public NullOrEmptyArgumentException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
