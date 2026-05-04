package com.tragsa.microalumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tragsa.microalumnos.entity.AlumnoEntity;

public interface AlumnoRepository extends JpaRepository<AlumnoEntity, String> {
	//lista de alumnos por curso
	List<AlumnoEntity> findByCurso(String curso);
	
	//lista de cursos disponibles. Utilizamos el método @Query para obtener los cursos distintos de la base de datos
	@org.springframework.data.jpa.repository.Query("SELECT DISTINCT a.curso FROM AlumnoEntity a")
	List<String> findDistinctCurso();
}
