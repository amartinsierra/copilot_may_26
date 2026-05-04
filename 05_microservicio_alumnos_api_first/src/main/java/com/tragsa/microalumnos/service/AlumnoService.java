package com.tragsa.microalumnos.service;

import java.util.List;

import com.tragsa.microalumnos.domain.Alumno;

public interface AlumnoService {
 List<Alumno> getAllAlumnos();
 Alumno getAlumnoByDni(String dni);
 List<Alumno> getAlumnosByCurso(String curso);
 Alumno createAlumno(Alumno alumno);
 Alumno deleteAlumno(String dni);
 List<String> getCursos();
}
