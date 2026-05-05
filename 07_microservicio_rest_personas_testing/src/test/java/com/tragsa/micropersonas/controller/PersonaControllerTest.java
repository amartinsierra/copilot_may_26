package com.tragsa.micropersonas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tragsa.micropersonas.domain.Persona;
import com.tragsa.micropersonas.service.PersonaService;

@WebMvcTest(PersonaController.class)
class PersonaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private PersonaService personaService;

	@Autowired
	private ObjectMapper objectMapper;

	private Persona persona;

	@BeforeEach
	void setUp() {
		persona = new Persona("Juan", "11111111A", 25, "juan@email.com");
	}

	@Test
	void crearPersona_WhenSuccess_ReturnsCreated() throws Exception {
		when(personaService.crearPersona(any(Persona.class))).thenReturn(persona);

		mockMvc.perform(post("/personas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(persona)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.dni").value("11111111A"))
				.andExpect(jsonPath("$.nombre").value("Juan"));
	}

	@Test
	void crearPersona_WhenConflict_ReturnsConflict() throws Exception {
		when(personaService.crearPersona(any(Persona.class))).thenReturn(null);

		mockMvc.perform(post("/personas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(persona)))
				.andExpect(status().isConflict());
	}

	@Test
	void obtenerPersonaPorDni_WhenExists_ReturnsOk() throws Exception {
		when(personaService.obtenerPersonaPorDni("11111111A")).thenReturn(persona);

		mockMvc.perform(get("/personas/11111111A"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.dni").value("11111111A"));
	}

	@Test
	void obtenerPersonaPorDni_WhenDoesNotExist_ReturnsNotFound() throws Exception {
		when(personaService.obtenerPersonaPorDni("nonexistent")).thenReturn(null);

		mockMvc.perform(get("/personas/nonexistent"))
				.andExpect(status().isNotFound());
	}

	@Test
	void obtenerTodasLasPersonas_ReturnsList() throws Exception {
		when(personaService.obtenerTodasLasPersonas()).thenReturn(List.of(persona));

		mockMvc.perform(get("/personas"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].dni").value("11111111A"));
	}

	@Test
	void eliminarPersona_WhenExists_ReturnsOk() throws Exception {
		when(personaService.eliminarPersona("11111111A")).thenReturn(persona);

		mockMvc.perform(delete("/personas/11111111A"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.dni").value("11111111A"));
	}

	@Test
	void eliminarPersona_WhenDoesNotExist_ReturnsNotFound() throws Exception {
		when(personaService.eliminarPersona("nonexistent")).thenReturn(null);

		mockMvc.perform(delete("/personas/nonexistent"))
				.andExpect(status().isNotFound());
	}
}