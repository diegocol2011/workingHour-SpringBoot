package com.calculadora.app.utils;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FormatTimeValidator implements ConstraintValidator<FormatTime, LocalTime> {

	private Pattern mask = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$");

	@Override
	public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
		final Matcher matcher = mask.matcher(value.toString());
		return (matcher.matches()) ? true : false;
	}
}
