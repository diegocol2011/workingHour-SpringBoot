package com.calculadora.app.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		List<ReporteEntity> reporteEntities = reporteDao.findByIdTecnicoAndFechaInicioAndFechaFin(
				id, dateList.get(0), dateList.get(1));

		return WorkingHour.CalculateWorkingHour(dateList.get(0), reporteEntities);
	}

}
