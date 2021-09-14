package com.calculadora.app.model.service;

import com.calculadora.app.model.entity.ReporteEntity;

public interface IReporteService {
	public ReporteEntity save(ReporteEntity reporteEntity);

	public Boolean validateDate(ReporteEntity reporteEntity);
}
