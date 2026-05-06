package com.tragsa.microlibros.dto;

/**
 * DTO para la entidad LibroEntity implementado como record.
 */
public record Libro(Integer isbn, String titulo, String tematica, Double precio) {
}
