package com.calculadora.app.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculadora.app.model.dao.IReporteDao;
import com.calculadora.app.model.entity.Reporte;
import com.calculadora.app.utils.Validacion;

@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
	private IReporteDao reporteDao;

	@Autowired
	private Validacion validacion;

	@Override
	public Reporte save(Reporte reporte) {
		return reporteDao.save(reporte);
	}

	public Map<String, String> validateDate(Reporte reporte) {
		return validacion.matches(reporte);
	}
}
