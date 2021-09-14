package com.calculadora.app.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculadora.app.model.dao.IReporteDao;
import com.calculadora.app.model.entity.ReporteEntity;
import com.calculadora.app.utils.ValidationField;

@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
	private IReporteDao reporteDao;

	@Override
	public ReporteEntity save(ReporteEntity reporteEntity) {
		
		return reporteDao.save(reporteEntity);
	}

	public Boolean validateDate(ReporteEntity reporteEntity) {
		return ValidationField.afterDate(reporteEntity);
	}
}
