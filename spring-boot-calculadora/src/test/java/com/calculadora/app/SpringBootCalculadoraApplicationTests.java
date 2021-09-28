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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.calculadora.app.model.entity.ReporteEntity;
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

		mvc.perform(MockMvcRequestBuilders.post(url + "/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reporteEntity)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.idReporte").exists())
				.andReturn();
	}

	@Test
	public void findWorkHour() throws Exception {
			
		ReporteEntity reporteEntityUno = new ReporteEntity();
		reporteEntityUno.setIdTecnico("4789");
		reporteEntityUno.setIdServicio("234");
		reporteEntityUno.setFechaInicio(LocalDate.parse("2021-09-06"));
		reporteEntityUno.setFechaFin(LocalDate.parse("2021-09-07"));
		reporteEntityUno.setHoraInicio(LocalTime.parse("14:16"));
		reporteEntityUno.setHoraFin(LocalTime.parse("14:16"));
		
		MvcResult resultSaveUno = mvc.perform(MockMvcRequestBuilders.post(url + "/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reporteEntityUno)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.idReporte").exists())
				.andReturn();
		
		ReporteEntity resultEntityUno = objectMapper.readValue(resultSaveUno.getResponse().getContentAsString(),
				ReporteEntity.class);
		
		
		ReporteEntity reporteEntityDos = new ReporteEntity();
		reporteEntityDos.setIdTecnico("4789");
		reporteEntityDos.setIdServicio("234");
		reporteEntityDos.setFechaInicio(LocalDate.parse("2021-09-07"));
		reporteEntityDos.setFechaFin(LocalDate.parse("2021-09-08"));
		reporteEntityDos.setHoraInicio(LocalTime.parse("15:00"));
		reporteEntityDos.setHoraFin(LocalTime.parse("15:00"));
		
		MvcResult resultSaveDos = mvc.perform(MockMvcRequestBuilders.post(url + "/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reporteEntityDos)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.idReporte").exists())
				.andReturn();
		
		ReporteEntity resultEntityDos = objectMapper.readValue(resultSaveDos.getResponse().getContentAsString(),
				ReporteEntity.class);
		
		
		ReporteEntity reporteEntityTres = new ReporteEntity();
		reporteEntityTres.setIdTecnico("4789");
		reporteEntityTres.setIdServicio("234");
		reporteEntityTres.setFechaInicio(LocalDate.parse("2021-09-12"));
		reporteEntityTres.setFechaFin(LocalDate.parse("2021-09-13"));
		reporteEntityTres.setHoraInicio(LocalTime.parse("14:00"));
		reporteEntityTres.setHoraFin(LocalTime.parse("14:00"));
		
		MvcResult resultSaveTres = mvc.perform(MockMvcRequestBuilders.post(url + "/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reporteEntityTres)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.idReporte").exists())
				.andReturn();
		
		ReporteEntity resultEntityTres = objectMapper.readValue(resultSaveTres.getResponse().getContentAsString(),
				ReporteEntity.class);

		
		mvc.perform(MockMvcRequestBuilders.get(url + "/find/4789/36")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.hourDiurnal", is(26)))
				.andExpect(jsonPath("$.hourDiurnalExtra", is(1)))
				.andExpect(jsonPath("$.hourNocturnal", is(22)))
				.andExpect(jsonPath("$.hourNocturnalExtra", is(0)))
				.andExpect(jsonPath("$.hourSunday", is(0)))
				.andExpect(jsonPath("$.hourSundayExtra", is(10)))
				.andReturn();
		
		
		mvc.perform(MockMvcRequestBuilders.delete(url + "/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(resultEntityUno)))
				.andExpect(status().isNoContent())
				.andReturn();
		
		mvc.perform(MockMvcRequestBuilders.delete(url + "/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(resultEntityDos)))
				.andExpect(status().isNoContent())
				.andReturn();
		
		mvc.perform(MockMvcRequestBuilders.delete(url + "/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(resultEntityTres)))
				.andExpect(status().isNoContent())
				.andReturn();
	}

}
