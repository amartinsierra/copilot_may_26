package com.tragsa.microalumnos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.tragsa.microalumnos.api.AlumnosApi;
import com.tragsa.microalumnos.domain.Alumno;
import com.tragsa.microalumnos.service.AlumnoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
@RestController
public class AlumnoController implements AlumnosApi {
	//declaramos el servicio para poder usarlo en los métodos y lo inyectamos con constructor
	private final AlumnoService alumnoService;
	public AlumnoController(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}
	@Override
	public ResponseEntity<Void> createAlumno(@Valid Alumno alumno) {
		if (alumnoService.createAlumno(alumno) != null) {
			return ResponseEntity.ok().build();
		}
		//error 409
		return ResponseEntity.status(409).build();
	}

	@Override
	public ResponseEntity<Void> deleteAlumno(String dni) {
		if (alumnoService.deleteAlumno(dni) != null) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<List<Alumno>> getAllAlumnos() {
		return ResponseEntity.ok(alumnoService.getAllAlumnos());
	}

	@Override
	public ResponseEntity<Alumno> getAlumnoByDni(String dni) {
		Alumno alumno = alumnoService.getAlumnoByDni(dni);
		if (alumno != null) {
			return ResponseEntity.ok(alumno);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<List<Alumno>> getAlumnosByCurso(@NotNull @Valid String curso) {
		List<Alumno> alumnos = alumnoService.getAlumnosByCurso(curso);
		if (alumnos != null && !alumnos.isEmpty()) {
			return ResponseEntity.ok(alumnos);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<List<String>> getCursos() {
		List<String> cursos = alumnoService.getCursos();
		if (cursos != null && !cursos.isEmpty()) {
			return ResponseEntity.ok(cursos);
		}
		return ResponseEntity.notFound().build();
	}

}
