package com.calculadora.app.model.service;

import java.util.Map;

import com.calculadora.app.model.entity.Reporte;

public interface IReporteService {
    public Reporte save(Reporte reporte);
    
    public Map<String, String> validateDate(Reporte reporte);
}
