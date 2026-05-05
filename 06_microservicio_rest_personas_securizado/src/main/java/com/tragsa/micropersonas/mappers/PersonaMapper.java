package com.tragsa.micropersonas.mappers;

import org.springframework.stereotype.Component;

import com.tragsa.micropersonas.domain.Persona;
import com.tragsa.micropersonas.entity.PersonaEntity;

@Component
public class PersonaMapper {
	// Métodos para convertir entre PersonaEntity y Persona
	public PersonaEntity toEntity(Persona persona) {
		if (persona == null) {
			return null;
		}
		PersonaEntity entity = new PersonaEntity();
		entity.setDni(persona.getDni());
		entity.setNombre(persona.getNombre());
		entity.setEdad(persona.getEdad());
		entity.setEmail(persona.getEmail());
		return entity;
	}

	public Persona toDomain(PersonaEntity entity) {
		if (entity == null) {
			return null;
		}
		Persona persona = new Persona();
		persona.setDni(entity.getDni());
		persona.setNombre(entity.getNombre());
		persona.setEdad(entity.getEdad());
		persona.setEmail(entity.getEmail());
		return persona;
	}
}
