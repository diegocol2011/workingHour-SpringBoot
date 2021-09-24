package com.calculadora.app.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculadora.app.model.Dto.WorkingHourDto;
import com.calculadora.app.model.entity.ReporteEntity;
import com.calculadora.app.model.service.IReporteService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ReporteController {

	@Autowired
	private IReporteService reporteService;

	@PostMapping("/save")
	public ResponseEntity<ReporteEntity> saveReport(@Valid @RequestBody ReporteEntity reporteEntity) {
		reporteService.afterDate(reporteEntity);

		return ResponseEntity.created(URI.create("/api")).contentType(MediaType.APPLICATION_JSON)
				.body(reporteService.save(reporteEntity));
	}
	
	@PutMapping("/update")
	public ResponseEntity<ReporteEntity> updateReport(@Valid @RequestBody ReporteEntity reporteEntity) {
		reporteService.afterDate(reporteEntity);

		return ResponseEntity.created(URI.create("/api")).contentType(MediaType.APPLICATION_JSON)
				.body(reporteService.update(reporteEntity));
	}
	

	@GetMapping("/find/{id}/{week}")
	public ResponseEntity<WorkingHourDto> findWorkingHour(@PathVariable String id, @PathVariable int week) {
		WorkingHourDto workingHourDto = reporteService.CalculateWorkingHour(id, week);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(workingHourDto);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<ReporteEntity> findReport(@PathVariable Long id) {
		ReporteEntity reporteEntity = reporteService.findById(id);

		return (reporteEntity != null) ? 
				ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(reporteEntity)
				: ResponseEntity.notFound().build();
	}

	@GetMapping("/find")
	public ResponseEntity<List<ReporteEntity>> findReports() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(reporteService.findAll());
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteReport(@RequestBody ReporteEntity reporteEntity) {
		try {
			reporteService.delete(reporteEntity);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
