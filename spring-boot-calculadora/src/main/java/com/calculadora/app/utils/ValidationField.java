package com.calculadora.app.utils;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Component;

import com.calculadora.app.model.entity.ReporteEntity;

@Component
public class ValidationField {

	public static Boolean afterDate(ReporteEntity reporteEntity) {

		try {
			if (reporteEntity.getFechaInicio().isAfter(reporteEntity.getFechaFin())) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new TypeMismatchException(reporteEntity, null);
		}

		return Boolean.FALSE;
	}
}
