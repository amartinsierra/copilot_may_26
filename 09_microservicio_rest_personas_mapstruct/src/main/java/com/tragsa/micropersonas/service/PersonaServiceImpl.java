package com.tragsa.micropersonas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tragsa.micropersonas.domain.Persona;
import com.tragsa.micropersonas.mappers.PersonaMapper;
import com.tragsa.micropersonas.repository.PersonaRepository;
@Service
public class PersonaServiceImpl implements PersonaService {
	//declara una variable PersonaRepository para acceder a la base de datos, inyectala por constructor
	private final PersonaRepository personaRepository;
	//declara una variable de tipo PersonaMapper para convertir entre Persona y PersonaEntity, inyectala por constructor
	private final PersonaMapper personaMapper;
	public PersonaServiceImpl(PersonaRepository personaRepository, PersonaMapper personaMapper) {
		this.personaRepository = personaRepository;
		this.personaMapper = personaMapper;
	}
	@Override
	public Persona crearPersona(Persona persona) {
		//Añade la persona a la base de datos, si el dni no existe en la base de datos, si el dni ya existe, devuelve null
		//utilizando los métodos del repositorio. No inventes métodos nuevos, utiliza los que ya existen en el repositorio
		//ten en cuenta que el dni es la primary key
		if (personaRepository.existsById(persona.getDni())) {
			return null;
		}
		//debes convertir Persona a PersonaEntity antes de guardarla en la base de datos, y convertirla de nuevo a Persona antes de devolverla
		return personaMapper.toDomain(personaRepository.save(personaMapper.toEntity(persona)));
				
	}

	@Override
	public Persona obtenerPersonaPorDni(String dni) {
		//Busca la persona en la base de datos por su dni, si no la encuentra devuelve null
		//utilizando los métodos del repositorio. No inventes métodos nuevos, utiliza los que ya existen en el repositorio
		//debes convertir PersonaEntity a Persona antes de devolverla
		return personaRepository.findById(dni)
				.map(personaMapper::toDomain)
				.orElse(null);
	}

	@Override
	public List<Persona> obtenerTodasLasPersonas() {
		//Devuelve la lista de todas las personas
		return personaRepository.findAll().stream()
				.map(personaMapper::toDomain)
				.toList();
	}

	@Override
	public Persona eliminarPersona(String dni) {
		//Elimina la persona de la lista por su dni, si no la encuentra devuelve null
		return personaRepository.findById(dni)
				.map(persona -> {
					personaRepository.delete(persona);
					return personaMapper.toDomain(persona);
				})
				.orElse(null);
	}

}
