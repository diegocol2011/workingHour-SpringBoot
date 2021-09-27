package com.calculadora.app.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.calculadora.app.model.Dto.WorkingHourDto;
import com.calculadora.app.model.entity.ReporteEntity;

public final class WorkingHour {

	public static WorkingHourDto CalculateWorkingHour(List<ReporteEntity> reporteEntities, LocalDate firstDayWeek,
			LocalDate lastDayWeek) {

		int hourBegin = 0, hourDiurnal = 0, hourDiurnalExtra = 0, hourNocturnal = 0,
				hourNocturnalExtra = 0, hourSunday = 0, hourSundayExtra = 0, regulationTime = 48;

		WorkingHourDto workingHourDto = new WorkingHourDto();

		LocalDateTime dateTimeBegin = null;
		LocalDateTime dateTimeEnd = null;
		LocalTime hourZero = LocalTime.of(00, 00, 00);

		for (ReporteEntity reporte : reporteEntities) {

			// Semana inicia el lunes - semana finaliza el domingo
			// Fecha inicio igual o despues del primer dia de la semana
			// De lo contrario, del rango de fechas, se inicia desde el primer dia de la
			// semana
			if ((reporte.getFechaInicio().isEqual(firstDayWeek) || reporte.getFechaInicio().isAfter(firstDayWeek))
					&& (reporte.getFechaInicio().isEqual(lastDayWeek) || reporte.getFechaInicio().isBefore(lastDayWeek))) {
				dateTimeBegin = LocalDateTime.of(reporte.getFechaInicio(), reporte.getHoraInicio());
				
				hourBegin = reporte.getHoraInicio().getHour();
				
			} else if ( ((reporte.getFechaFin().isEqual(firstDayWeek) || reporte.getFechaFin().isAfter(firstDayWeek))
						&& (reporte.getFechaFin().isEqual(lastDayWeek) || reporte.getFechaFin().isBefore(lastDayWeek)))
							|| (reporte.getFechaInicio().isBefore(firstDayWeek) && reporte.getFechaFin().isAfter(firstDayWeek)) ) {
				dateTimeBegin = LocalDateTime.of(firstDayWeek, hourZero);
				
				hourBegin = hourZero.getHour();
				
			}

			if(dateTimeBegin != null) {
				dateTimeEnd = LocalDateTime.of(reporte.getFechaFin(), reporte.getHoraFin());
				
				if(reporte.getHoraInicio().getMinute() > 0) {
					 dateTimeBegin = dateTimeBegin.truncatedTo(ChronoUnit.HOURS);
				}
				
				if(reporte.getHoraFin().getMinute() > 0) {
					 dateTimeEnd = dateTimeEnd.truncatedTo(ChronoUnit.HOURS).plusHours(1);
				}
				
			} else {
				continue;
			}
			
			Duration duration = Duration.between(dateTimeBegin, dateTimeEnd);
			long workingHour = Math.abs(duration.getSeconds()) / 3600;
			
			int dayWeek = dateTimeBegin.getDayOfWeek().getValue();

			for (int i = 1; i <= workingHour; i++) {
				if (dayWeek == 8) {
					break;
				}

				if (dayWeek != 7) {

					if (hourBegin >= 7 && hourBegin <= 19) {
						hourDiurnal++;
					}

					if ((hourBegin >= 20 && hourBegin <= 23) || (hourBegin >= 0 && hourBegin <= 6)) {
						hourNocturnal++;
					}

					hourBegin++;

					if (hourBegin == 24) {
						hourBegin = 0;
						dayWeek++;
					}

				} else {
					if ((hourBegin >= 0 && hourBegin <= 23)) {
						hourSunday++;
					}

					hourBegin++;

					if (hourBegin == 24) {
						hourBegin = 0;
						dayWeek++;
					}

				}

			}

		}

		if (hourDiurnal > regulationTime) {
			hourDiurnalExtra = hourDiurnal - regulationTime;
			hourDiurnal -= hourDiurnalExtra;
		}

		if (hourNocturnal > regulationTime) {
			hourNocturnalExtra = hourNocturnal - regulationTime;
			hourNocturnal -= hourNocturnalExtra;
		}

		workingHourDto.setHourDiurnal(hourDiurnal);
		workingHourDto.setHourDiurnalExtra(hourDiurnalExtra);
		workingHourDto.setHourNocturnal(hourNocturnal);
		workingHourDto.setHourNocturnalExtra(hourNocturnalExtra);
		workingHourDto.setHourSunday(hourSunday);
		workingHourDto.setHourSundayExtra(hourSundayExtra);

		return workingHourDto;

	}
}
