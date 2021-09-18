package com.calculadora.app;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.calculadora.app.model.entity.ReporteEntity;
import com.calculadora.app.model.service.IReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class SpringBootCalculadoraApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private IReporteService service;

	@Value("${config.base.endpoint}")
	private String url;

	@Test
	public void saveReport() throws Exception {

		ReporteEntity reporteEntity = new ReporteEntity();
		reporteEntity.setIdTecnico("678");
		reporteEntity.setIdServicio("234");
		reporteEntity.setFechaInicio(LocalDate.parse("2021-09-10"));
		reporteEntity.setFechaFin(LocalDate.parse("2021-09-13"));
		reporteEntity.setHoraInicio(LocalTime.parse("10:00"));
		reporteEntity.setHoraFin(LocalTime.parse("09:00"));

		mvc.perform(MockMvcRequestBuilders.post(url + "/guardar").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reporteEntity))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.idReporte").exists()).andReturn();
	}

	@Test
	public void findWorkHour() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(url + "/find/123/36").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.hourDiurnal", is(48)))
		.andExpect(jsonPath("$.hourDiurnalExtra", is(24)))
		.andExpect(jsonPath("$.hourNocturnal", is(48)))
		.andExpect(jsonPath("$.hourNocturnalExtra", is(18)))
		.andExpect(jsonPath("$.hourSunday", is(0)))
		.andExpect(jsonPath("$.hourSundayExtra", is(0)))
		.andReturn();
	}

}
