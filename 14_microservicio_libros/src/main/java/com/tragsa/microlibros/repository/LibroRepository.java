package com.tragsa.microlibros.repository;

import com.tragsa.microlibros.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<LibroEntity, Integer> {
    List<LibroEntity> findByPrecioBetween(Double precioMin, Double precioMax);
}
