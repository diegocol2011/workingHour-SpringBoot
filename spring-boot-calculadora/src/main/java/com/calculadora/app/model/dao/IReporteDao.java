package com.calculadora.app.model.dao;

import com.calculadora.app.model.entity.ReporteEntity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IReporteDao extends CrudRepository<ReporteEntity,Long> {
	
	@Query("select r from ReporteEntity r where r.idTecnico = ?1 and r.fechaInicio >= ?2")// and r.fechaFin <= ?3" )
	public List<ReporteEntity> findByIdTecnicoAndFechaInicioAndFechaFin(String idTecnico, LocalDate fechaInicio, LocalDate fechaFin);


	List<ReporteEntity> findByIdTecnico(String idTecnico);
}
