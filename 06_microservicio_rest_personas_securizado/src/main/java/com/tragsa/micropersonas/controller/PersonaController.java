package com.tragsa.micropersonas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tragsa.micropersonas.domain.Persona;
import com.tragsa.micropersonas.service.PersonaService;


@RestController
public class PersonaController {
	//Declara una variable de tipo PersonaService e inyectala con constructor
	private final PersonaService personaService;
	public PersonaController(PersonaService personaService) {
		this.personaService = personaService;
	}
	
	//Implementa los métodos del controlador para cada una de las operaciones del servicio
	//devuelve siempre response entity con el resultado de la operación y el código de estado correspondiente
	
	@PostMapping("/personas")
	public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
		Persona nuevaPersona = personaService.crearPersona(persona);
		if (nuevaPersona == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
	}
	
	@GetMapping("/personas/{dni}")
	public ResponseEntity<Persona> obtenerPersonaPorDni(@PathVariable String dni) {
		Persona persona = personaService.obtenerPersonaPorDni(dni);
		if (persona == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(persona);
	}
	
	@GetMapping("/personas")
	public ResponseEntity<List<Persona>> obtenerTodasLasPersonas() {
		return ResponseEntity.ok(personaService.obtenerTodasLasPersonas());
	}
	@DeleteMapping("/personas/{dni}")
	public ResponseEntity<Persona> eliminarPersona(@PathVariable String dni) {
		Persona personaEliminada = personaService.eliminarPersona(dni);
		if (personaEliminada == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(personaEliminada);
	}
}
