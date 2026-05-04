package com.tragsa.micropersonas.service;

import java.util.List;

import com.tragsa.micropersonas.domain.Persona;

public interface PersonaService {
	// Métodos del servicio
	Persona crearPersona(Persona persona);
	Persona obtenerPersonaPorDni(String dni);
	List<Persona> obtenerTodasLasPersonas();
	Persona eliminarPersona(String dni);
}
