package com.calculadora.app.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

public final class Convertion {

	public static List<LocalDate> WeekToDate(int week) {
		List<LocalDate> date = new ArrayList<>();

		LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), 2, 1)
				.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week)
				.with(ChronoField.DAY_OF_WEEK, DayOfWeek.MONDAY.getValue());

		date.add(localDate);
		date.add(localDate.plusDays(6));
		return date;
	}

}
