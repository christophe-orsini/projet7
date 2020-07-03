package com.ocdev.biblio.apibiblio.exceptions;

/**
 * Classe exception pour recherche non trouvé et obligatoire.
 * Levé lorsque une recherche n'aboutit pas et qu'elle devrait.
 * @author C.Orsini
 */
public class RequiredAndNotFoundException extends BiblioException
{

	public RequiredAndNotFoundException()
	{
		super();
	}

	public RequiredAndNotFoundException(String message)
	{
		super(message);
	}
	
	public RequiredAndNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
