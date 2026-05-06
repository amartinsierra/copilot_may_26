package com.tragsa.microlibros.mapper;

import com.tragsa.microlibros.dto.Libro;
import com.tragsa.microlibros.entity.LibroEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibroMapper {
    Libro toDto(LibroEntity entity);
    LibroEntity toEntity(Libro dto);
}
