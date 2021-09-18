package com.calculadora.app.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.calculadora.app.model.Dto.WorkingHourDto;
import com.calculadora.app.model.entity.ReporteEntity;

public final class WorkingHour {

	public static WorkingHourDto CalculateWorkingHour(List<ReporteEntity> reporteEntities) {

		int hourDiurnal = 0, hourDiurnalExtra = 0, hourNocturnal = 0, hourNocturnalExtra = 0, hourSunday = 0,
				hourSundayExtra = 0, regulationTime = 48;

		WorkingHourDto workingHourDto = new WorkingHourDto();

		for (ReporteEntity reporte : reporteEntities) {

			LocalDateTime dateTimeBegin = LocalDateTime.of(reporte.getFechaInicio(), reporte.getHoraInicio());
			LocalDateTime dateTimeEnd = LocalDateTime.of(reporte.getFechaFin(), reporte.getHoraFin());

			Duration duration = Duration.between(dateTimeBegin, dateTimeEnd);
			long workingHour = Math.abs(duration.getSeconds()) / 3600;

			int hour = reporte.getHoraInicio().getHour();

			int dayWeek = dateTimeBegin.getDayOfWeek().getValue();

			for (int i = 1; i <= workingHour; i++) {
				if (dayWeek == 8) {
					break;
				}

				if (dayWeek != 7) {

					if ((hour >= 0 && hour <= 6)) {
						hourNocturnal++;
					}

					if (hour >= 7 && hour <= 19) {
						hourDiurnal++;
					}

					if (hour >= 20 && hour <= 23) {
						hourNocturnal++;
					}

					hour++;

					if (hour == 24) {
						hour = 0;
						dayWeek++;
					}

				} else {
					if ((hour >= 0 && hour <= 23)) {
						hourSunday++;
					}

					hour++;

					if (hour == 24) {
						hour = 0;
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
