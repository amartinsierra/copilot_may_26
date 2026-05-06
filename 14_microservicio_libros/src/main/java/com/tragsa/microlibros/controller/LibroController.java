package com.tragsa.microlibros.controller;

import com.tragsa.microlibros.dto.Libro;
import com.tragsa.microlibros.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodos() {
        return new ResponseEntity<>(libroService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/rango")
    public ResponseEntity<List<Libro>> obtenerPorRangoPrecio(
            @RequestParam("min") Double precioMin,
            @RequestParam("max") Double precioMax) {
        return new ResponseEntity<>(libroService.obtenerPorRangoPrecio(precioMin, precioMax), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Libro> darDeAlta(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.darDeAlta(libro);
        if (nuevoLibro == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> eliminarPorIsbn(@PathVariable("isbn") Integer isbn) {
        libroService.eliminarPorIsbn(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
