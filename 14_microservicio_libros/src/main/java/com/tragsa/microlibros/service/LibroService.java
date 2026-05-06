package com.tragsa.microlibros.service;

import com.tragsa.microlibros.dto.Libro;
import java.util.List;

public interface LibroService {
    List<Libro> obtenerTodos();
    List<Libro> obtenerPorRangoPrecio(Double precioMin, Double precioMax);
    Libro darDeAlta(Libro libro);
    void eliminarPorIsbn(Integer isbn);
}
