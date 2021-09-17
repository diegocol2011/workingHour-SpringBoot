package com.calculadora.app.exceptions;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();

		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	/*
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * @ExceptionHandler(HttpMessageNotReadableException.class) public Map<String,
	 * String> handleHttpMessageNotReadableException(HttpMessageNotReadableException
	 * exception) { Map<String, String> errors = new HashMap<>();
	 * 
	 * if
	 * (exception.getMostSpecificCause().getClass().equals(DateTimeParseException.
	 * class)) { errors.put("Fecha",
	 * "Las fechas deben tener el formato yyyy-MM-dd"); return errors; } return
	 * errors; }
	 */

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DateTimeParseException.class)
	public Map<String, String> handleException(DateTimeParseException exception) {
		Map<String, String> errors = new HashMap<>();
		errors.put("Message: ", "Fecha debe tener formato yyyy-MM-dd"
				+ "     Hora debe tener formato HH:MM");
		return errors;

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(TypeMismatchException.class)
	public Map<String, String> handleException(TypeMismatchException exception) {
		Map<String, String> errors = new HashMap<>();
		errors.put("Message: ", "La fecha de inicio debe ser menor que la fecha de fin");
		return errors;

	}

}
