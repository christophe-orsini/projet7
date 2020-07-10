package com.ocdev.biblio.apibiblio.errors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ErrorHandlingControllerAdvice
{
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e)
	{
		ValidationErrorResponse error = new ValidationErrorResponse();
		for (ConstraintViolation<?> violation : e.getConstraintViolations())
		{
			error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
		}
		return error;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) 
	{
		ValidationErrorResponse error = new ValidationErrorResponse();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors())
		{
			error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
		}
		return error;
    }
	
	@ExceptionHandler({BiblioException.class, AlreadyExistsException.class, DuplicateLoanException.class,
		DelayLoanException.class, NotEnoughCopiesException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	ValidationErrorResponse onBiblioException(BiblioException e)
	{
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getViolations().add(new Violation(null, e.getMessage()));
		return error;
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	String onEntityNotFoundException(EntityNotFoundException e)
	{
		return e.getMessage();
	}
}
