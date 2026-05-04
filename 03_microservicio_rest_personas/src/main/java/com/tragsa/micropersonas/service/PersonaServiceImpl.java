package com.tragsa.micropersonas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tragsa.micropersonas.domain.Persona;
@Service
public class PersonaServiceImpl implements PersonaService {
	List<Persona> personas;
	//crea un constructor en el que se inicialice la lista de personas
	//con un arraylist de cinco personas con datos ficticios
	public PersonaServiceImpl() {
		personas = new ArrayList<>(List.of(
				new Persona("Juan Pérez", "12345678A", 30, "juan.com"),
				new Persona("María García", "87654321B", 25, "maria.com"),
				new Persona("Carlos López", "11223344C", 40, "carlos.com"),
				new Persona("Ana Martínez", "44332211D", 35, "ana.com"),
				new Persona("Luis Rodríguez", "55667788E", 28, "luis.com")
				));
		}
	
	@Override
	public Persona crearPersona(Persona persona) {
		//Añade la persona a la lista de personas, si el dni
		//no existe en la lista, si el dni ya existe, devuelve null
		//utiliza programacion funional
		if (personas.stream().anyMatch(p -> p.getDni().equals(persona.getDni()))) {
			return null;
		}
		personas.add(persona);
		return persona;
	}

	@Override
	public Persona obtenerPersonaPorDni(String dni) {
		//Busca la persona en la lista por su dni, si no la encuentra devuelve null
		return personas.stream()
				.filter(p -> p.getDni().equals(dni))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Persona> obtenerTodasLasPersonas() {
		//Devuelve la lista de todas las personas
		return personas;
	}

	@Override
	public Persona eliminarPersona(String dni) {
		//Elimina la persona de la lista por su dni, si no la encuentra devuelve null
		Persona persona = obtenerPersonaPorDni(dni);
		if (persona != null) {
			personas.remove(persona);
		}
		return persona;
	}

}
