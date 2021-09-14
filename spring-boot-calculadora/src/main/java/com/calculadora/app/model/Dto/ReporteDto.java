package com.calculadora.app.model.Dto;

import org.springframework.http.ResponseEntity;

import com.calculadora.app.model.entity.ReporteEntity;

public class ReporteDto {
	
	private String idTecnico;

	private String idServicio;

	private String fechaInicio;

	private String fechaFin;

	private String horaInicio;

	private String horaFin;
	
	public ReporteDto() {
	}
	
	public ReporteDto(ReporteEntity reporteEntity) {
		this.idTecnico = reporteEntity.getIdTecnico();
		this.idServicio = reporteEntity.getIdServicio();
		this.fechaInicio = reporteEntity.getFechaInicio().toString();
		this.fechaFin = reporteEntity.getFechaFin().toString();
		this.horaInicio = reporteEntity.getHoraInicio();
		this.horaFin = reporteEntity.getHoraFin();
	}

	public String getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(String idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
}
