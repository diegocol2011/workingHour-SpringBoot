package com.calculadora.app.model.Dto;

public class WorkingHourDto {

	public int hourDiurnal;

	public int hourDiurnalExtra;

	public int hourNocturnal;

	public int hourNocturnalExtra;

	public int hourSunday;

	public int hourSundayExtra;

	public WorkingHourDto() {
	}

	public int getHourDiurnal() {
		return hourDiurnal;
	}

	public void setHourDiurnal(int hourDiurnal) {
		this.hourDiurnal = hourDiurnal;
	}

	public int getHourDiurnalExtra() {
		return hourDiurnalExtra;
	}

	public void setHourDiurnalExtra(int hourDiurnalExtra) {
		this.hourDiurnalExtra = hourDiurnalExtra;
	}

	public int getHourNocturnal() {
		return hourNocturnal;
	}

	public void setHourNocturnal(int hourNocturnal) {
		this.hourNocturnal = hourNocturnal;
	}

	public int getHourNocturnalExtra() {
		return hourNocturnalExtra;
	}

	public void setHourNocturnalExtra(int hourNocturnalExtra) {
		this.hourNocturnalExtra = hourNocturnalExtra;
	}

	public int getHourSunday() {
		return hourSunday;
	}

	public void setHourSunday(int hourSunday) {
		this.hourSunday = hourSunday;
	}

	public int getHourSundayExtra() {
		return hourSundayExtra;
	}

	public void setHourSundayExtra(int hourSundayExtra) {
		this.hourSundayExtra = hourSundayExtra;
	}

	@Override
	public String toString() {
		return "WorkingHourDto [hourDiurnal=" + hourDiurnal + ", hourDiurnalExtra=" + hourDiurnalExtra
				+ ", hourNocturnal=" + hourNocturnal + ", hourNocturnalExtra=" + hourNocturnalExtra + ", hourSunday="
				+ hourSunday + ", hourSundayExtra=" + hourSundayExtra + "]";
	}
}
