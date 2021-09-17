package com.calculadora.app.model.Dto;

public class WorkingHourDto {

	public int horaDiurna;

	public int horaDiurnaExtra;

	public int horaNocturna;

	public int horaNocturnaExtra;

	public int horaDominical;

	public int horaDominicalExtra;
	
	public WorkingHourDto() {
	}

	public int getHoraDiurna() {
		return horaDiurna;
	}

	public void setHoraDiurna(int horaDiurna) {
		this.horaDiurna = horaDiurna;
	}

	public int getHoraDiurnaExtra() {
		return horaDiurnaExtra;
	}

	public void setHoraDiurnaExtra(int horaDiurnaExtra) {
		this.horaDiurnaExtra = horaDiurnaExtra;
	}

	public int getHoraNocturna() {
		return horaNocturna;
	}

	public void setHoraNocturna(int horaNocturna) {
		this.horaNocturna = horaNocturna;
	}

	public int getHoraNocturnaExtra() {
		return horaNocturnaExtra;
	}

	public void setHoraNocturnaExtra(int horaNocturnaExtra) {
		this.horaNocturnaExtra = horaNocturnaExtra;
	}

	public int getHoraDominical() {
		return horaDominical;
	}

	public void setHoraDominical(int horaDominical) {
		this.horaDominical = horaDominical;
	}

	public int getHoraDominicalExtra() {
		return horaDominicalExtra;
	}

	public void setHoraDominicalExtra(int horaDominicalExtra) {
		this.horaDominicalExtra = horaDominicalExtra;
	}

	@Override
	public String toString() {
		return "WorkingHourDto [horaDiurna=" + horaDiurna + ", horaDiurnaExtra=" + horaDiurnaExtra + ", horaNocturna="
				+ horaNocturna + ", horaNocturnaExtra=" + horaNocturnaExtra + ", horaDominical=" + horaDominical
				+ ", horaDominicalExtra=" + horaDominicalExtra + "]";
	}
}
