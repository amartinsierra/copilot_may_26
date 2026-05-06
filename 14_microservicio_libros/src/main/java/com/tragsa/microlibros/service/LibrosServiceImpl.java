package com.tragsa.microlibros.service;

import com.tragsa.microlibros.dto.Libro;
import com.tragsa.microlibros.entity.LibroEntity;
import com.tragsa.microlibros.mapper.LibroMapper;
import com.tragsa.microlibros.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibrosServiceImpl implements LibroService {

    private final LibroRepository repository;
    private final LibroMapper mapper;

    public LibrosServiceImpl(LibroRepository repository, LibroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Libro> obtenerTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> obtenerPorRangoPrecio(Double precioMin, Double precioMax) {
        return repository.findByPrecioBetween(precioMin, precioMax).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Libro darDeAlta(Libro libro) {
        if (repository.existsById(libro.isbn())) {
            return null; // O lanzar una excepción personalizada según política del proyecto
        }
        LibroEntity entity = mapper.toEntity(libro);
        LibroEntity saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void eliminarPorIsbn(Integer isbn) {
        if (repository.existsById(isbn)) {
            repository.deleteById(isbn);
        }
    }
}
