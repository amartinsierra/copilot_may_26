package com.tragsa.microcliente.service;

import java.util.List;

import com.tragsa.microcliente.dto.Alumno;

public interface AlumnosService {
	List<Alumno> getAlumnosCalificacionMinima(Float calificacionMinima);
}
