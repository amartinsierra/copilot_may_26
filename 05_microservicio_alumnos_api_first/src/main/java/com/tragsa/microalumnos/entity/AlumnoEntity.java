package com.tragsa.microalumnos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos")
public class AlumnoEntity {
	@Id
	private String dni;
	private String nombre;
	private String email;
	private String curso;
	private Float calificacion;

	public AlumnoEntity() {
		super();
	}

	public AlumnoEntity(String dni, String nombre, String email, String curso, Float calificacion) {
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.curso = curso;
		this.calificacion = calificacion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}
}
