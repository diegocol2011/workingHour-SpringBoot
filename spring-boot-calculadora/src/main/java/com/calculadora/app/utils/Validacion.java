package com.calculadora.app.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.calculadora.app.model.entity.Reporte;

@Component
public class Validacion {

	private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

	public Map<String, String> matches(Reporte reporte) {
		Map<String, String> validate = new HashMap();

		boolean fecha = DATE_PATTERN.matcher(reporte.getFechaInicio().toString()).matches();
		System.out.println("HASMAP");
		System.out.println(fecha);
		return validate;
	}
}
