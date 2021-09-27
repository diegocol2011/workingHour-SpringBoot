package com.calculadora.app.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.calculadora.app.model.entity.ReporteEntity;

public interface IReporteDao extends CrudRepository<ReporteEntity, Long> {

	public List<ReporteEntity> findByIdTecnico(String idTecnico);
}
