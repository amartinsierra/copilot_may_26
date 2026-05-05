package com.tragsa.micropersonas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tragsa.micropersonas.domain.Persona;
import com.tragsa.micropersonas.entity.PersonaEntity;
import com.tragsa.micropersonas.mappers.PersonaMapper;
import com.tragsa.micropersonas.repository.PersonaRepository;

@ExtendWith(MockitoExtension.class)
class PersonaServiceImplTest {

	@Mock
	private PersonaRepository personaRepository;

	@Mock
	private PersonaMapper personaMapper;

	@InjectMocks
	private PersonaServiceImpl personaService;

	private Persona persona;
	private PersonaEntity personaEntity;

	@BeforeEach
	void setUp() {
		persona = new Persona("Juan", "11111111A", 25, "juan@email.com");
		personaEntity = new PersonaEntity("Juan", "11111111A", 25, "juan@email.com");
	}

	@Test
	void crearPersona_WhenDniDoesNotExist_ReturnsCreatedPersona() {
		when(personaRepository.existsById(persona.getDni())).thenReturn(false);
		when(personaMapper.toEntity(persona)).thenReturn(personaEntity);
		when(personaRepository.save(personaEntity)).thenReturn(personaEntity);
		when(personaMapper.toDomain(personaEntity)).thenReturn(persona);

		Persona result = personaService.crearPersona(persona);

		assertThat(result).isNotNull();
		assertThat(result.getDni()).isEqualTo(persona.getDni());
		verify(personaRepository).save(any(PersonaEntity.class));
	}

	@Test
	void crearPersona_WhenDniExists_ReturnsNull() {
		when(personaRepository.existsById(persona.getDni())).thenReturn(true);

		Persona result = personaService.crearPersona(persona);

		assertThat(result).isNull();
		verify(personaRepository, never()).save(any(PersonaEntity.class));
	}

	@Test
	void obtenerPersonaPorDni_WhenExists_ReturnsPersona() {
		when(personaRepository.findById("11111111A")).thenReturn(Optional.of(personaEntity));
		when(personaMapper.toDomain(personaEntity)).thenReturn(persona);

		Persona result = personaService.obtenerPersonaPorDni("11111111A");

		assertThat(result).isNotNull();
		assertThat(result.getDni()).isEqualTo("11111111A");
	}

	@Test
	void obtenerPersonaPorDni_WhenDoesNotExist_ReturnsNull() {
		when(personaRepository.findById("nonexistent")).thenReturn(Optional.empty());

		Persona result = personaService.obtenerPersonaPorDni("nonexistent");

		assertThat(result).isNull();
	}

	@Test
	void obtenerTodasLasPersonas_ReturnsList() {
		when(personaRepository.findAll()).thenReturn(List.of(personaEntity));
		when(personaMapper.toDomain(personaEntity)).thenReturn(persona);

		List<Persona> result = personaService.obtenerTodasLasPersonas();

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getNombre()).isEqualTo("Juan");
	}

	@Test
	void eliminarPersona_WhenExists_ReturnsDeletedPersona() {
		when(personaRepository.findById("11111111A")).thenReturn(Optional.of(personaEntity));
		when(personaMapper.toDomain(personaEntity)).thenReturn(persona);

		Persona result = personaService.eliminarPersona("11111111A");

		assertThat(result).isNotNull();
		verify(personaRepository).delete(personaEntity);
	}

	@Test
	void eliminarPersona_WhenDoesNotExist_ReturnsNull() {
		when(personaRepository.findById("nonexistent")).thenReturn(Optional.empty());

		Persona result = personaService.eliminarPersona("nonexistent");

		assertThat(result).isNull();
		verify(personaRepository, never()).delete(any());
	}
}
