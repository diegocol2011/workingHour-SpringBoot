package com.calculadora.app.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.calculadora.app.model.Dto.WorkingHourDto;
import com.calculadora.app.model.entity.ReporteEntity;

public final class WorkingHour {

	public static WorkingHourDto CalculateWorkingHour(LocalDate startDate, List<ReporteEntity> reporteEntities) {
		// Lunes a sabado
		// LocalDate unoDate = dateList.get(0);
		// LocalDate dosDate = dateList.get(1);

		int hd = 0, hde = 0, hn = 0, hne = 0, hdo = 0, hdoe = 0;

		WorkingHourDto workingHourDto = new WorkingHourDto();

		for (ReporteEntity reporte : reporteEntities) {

			LocalDateTime dateTimeBegin = LocalDateTime.of(reporte.getFechaInicio(), reporte.getHoraInicio());
			LocalDateTime dateTimeEnd = LocalDateTime.of(reporte.getFechaFin(), reporte.getHoraFin());

			Duration duration = Duration.between(dateTimeBegin, dateTimeEnd);
			long workingHour = Math.abs(duration.getSeconds()) / 3600;

			int hour = reporte.getHoraInicio().getHour();

			for (int i = 1; i <= workingHour; i++) {

				// Hour nocturnal
				if ((hour >= 20 && hour <= 24) || (hour >= 1 && hour <= 6)) {
					hn++;
				}

				// Hora daytime
				if (hour >= 7 && hour <= 19) {
					hd++;
				}

				if (hour == 24) {
					hour = 0;
				}

				hour++;
			}

		}

		if (hd > 48) {
			hde = hd - 48;
			hd = hd - hde;
		}

		if (hn > 48) {
			hne = hn - 48;
			hn = hn - hne;
		}

		workingHourDto.setHoraDiurna(hd);
		workingHourDto.setHoraDiurnaExtra(hde);
		workingHourDto.setHoraNocturna(hn);
		workingHourDto.setHoraNocturnaExtra(hne);
		System.out.println(workingHourDto.toString());

		return workingHourDto;

	}
}
