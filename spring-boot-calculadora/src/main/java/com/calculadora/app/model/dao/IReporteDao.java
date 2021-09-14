package com.calculadora.app.model.dao;

import com.calculadora.app.model.entity.ReporteEntity;
import org.springframework.data.repository.CrudRepository;

public interface IReporteDao extends CrudRepository<ReporteEntity,Long> {

}
