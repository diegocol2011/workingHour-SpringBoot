package com.calculadora.app.model.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calculadora.app.model.Dto.WorkingHourDto;
import com.calculadora.app.model.dao.IReporteDao;
import com.calculadora.app.model.entity.ReporteEntity;
import com.calculadora.app.utils.Convertion;
import com.calculadora.app.utils.ValidationField;
import com.calculadora.app.utils.WorkingHour;

@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
	private IReporteDao reporteDao;

	@Override
	public ReporteEntity save(ReporteEntity reporteEntity) {
		return reporteDao.save(reporteEntity);
	}

	@Override
	public Boolean afterDate(ReporteEntity reporteEntity) {
		return ValidationField.afterDate(reporteEntity);
	}

	@Override
	public WorkingHourDto CalculateWorkingHour(String id, int week) {
		List<LocalDate> dateList = Convertion.WeekToDate(week);

		List<ReporteEntity> reporteEntities = reporteDao.findByIdTecnicoAndFechaInicioAndFechaFin(id, dateList.get(0),
				dateList.get(1));

		return WorkingHour.CalculateWorkingHour(reporteEntities, dateList.get(0));
	}

	@Override
	@Transactional(readOnly = true)
	public ReporteEntity findById(Long id) {
		return reporteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ReporteEntity> findAll() {
		List<ReporteEntity> reporteEntities = (List<ReporteEntity>) reporteDao.findAll();
		Collections.sort(reporteEntities,
				Comparator.comparing(ReporteEntity::getFechaInicio).thenComparing(ReporteEntity::getHoraInicio));

		return reporteEntities;
	}

	@Override
	public void delete(ReporteEntity reporteEntity) {
		reporteDao.delete(reporteEntity);
	}

	@Override
	public ReporteEntity update(ReporteEntity reporteEntity) {
		return reporteDao.save(reporteEntity);
	}
}
