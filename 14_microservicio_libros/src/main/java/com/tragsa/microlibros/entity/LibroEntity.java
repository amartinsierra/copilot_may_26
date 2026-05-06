package com.tragsa.microlibros.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad creada según skill
 */
@Entity
@Table(name = "libros")
public class LibroEntity {

    @Id
    private Integer isbn;
    private String titulo;
    private String tematica;
    private Double precio;

    public LibroEntity() {
    }

    public LibroEntity(Integer isbn, String titulo, String tematica, Double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.tematica = tematica;
        this.precio = precio;
    }

    public LibroEntity(String titulo, String tematica, Double precio) {
        this.titulo = titulo;
        this.tematica = tematica;
        this.precio = precio;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
