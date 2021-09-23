package com.calculadora.app.model.dao;

import com.calculadora.app.model.entity.ReporteEntity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IReporteDao extends CrudRepository<ReporteEntity,Long> {
	
	@Query("select r from ReporteEntity r where r.idTecnico = ?1 and (?2 between r.fechaInicio and r.fechaFin or ?3 between r.fechaInicio and r.fechaFin) ")
	public List<ReporteEntity> findByIdTecnicoAndFechaInicioAndFechaFin(String idTecnico, LocalDate fechaInicio, LocalDate fechaFin);

}
