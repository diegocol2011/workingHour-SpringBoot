package com.calculadora.app.model.service;

import java.util.List;

import com.calculadora.app.model.Dto.WorkingHourDto;
import com.calculadora.app.model.entity.ReporteEntity;

public interface IReporteService {
	
	public ReporteEntity save(ReporteEntity reporteEntity);

	public Boolean afterDate(ReporteEntity reporteEntity);
	
	public WorkingHourDto CalculateWorkingHour(String id, int week);
	
	public List<ReporteEntity> findAll();
	
}
