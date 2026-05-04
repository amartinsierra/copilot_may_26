package com.tragsa.microalumnos.mapper;

import org.springframework.stereotype.Component;

import com.tragsa.microalumnos.domain.Alumno;
import com.tragsa.microalumnos.entity.AlumnoEntity;

@Component
public class AlumnoMapper {
	// Método para convertir AlumnoEntity a Alumno y viceversa
	public Alumno toAlumno(AlumnoEntity alumnoEntity) {
		if (alumnoEntity == null) {
			return null;
		}
		return new Alumno(
			alumnoEntity.getDni(),
			alumnoEntity.getNombre(),
			alumnoEntity.getEmail(),
			alumnoEntity.getCurso(),
			alumnoEntity.getCalificacion()
		);
	}
	// Método para convertir Alumno a AlumnoEntity
	public AlumnoEntity toAlumnoEntity(Alumno alumno) {
		if (alumno == null) {
			return null;
		}
		return new AlumnoEntity(
			alumno.getDni(),
			alumno.getNombre(),
			alumno.getEmail(),
			alumno.getCurso(),
			alumno.getCalificacion()
		);
	}
}
