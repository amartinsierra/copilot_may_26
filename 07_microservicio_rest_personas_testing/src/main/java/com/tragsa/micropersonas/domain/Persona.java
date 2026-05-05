package com.tragsa.micropersonas.domain;

public class Persona {
	private String nombre;
	private String dni;
	private int edad;
	private String email;
	// Constructor vacío
	public Persona() {
	}
	//constructor con parámetros
	public Persona(String nombre, String dni, int edad, String email) {
		this.nombre = nombre;
		this.dni = dni;
		this.edad = edad;
		this.email = email;
	}
	// Getters y Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
