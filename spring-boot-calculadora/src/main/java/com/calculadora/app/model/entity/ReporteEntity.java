package com.calculadora.app.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.calculadora.app.utils.FormatTime;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Entity
@Table(name = "reporte")
public class ReporteEntity implements Serializable {

	private static final long serialVersionUID = 4967269355992515028L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReporte;

	@NotEmpty
	private String idTecnico;

	@NotEmpty
	private String idServicio;

	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate fechaInicio;

	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate fechaFin;

	@NotEmpty
	@FormatTime
	private String horaInicio;

	@NotEmpty
	@FormatTime
	private String horaFin;

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
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

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
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