package com.tragsa.microbuscador.mappers;

import com.tragsa.microbuscador.dto.ItemDto;
import com.tragsa.microbuscador.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "idItem", ignore = true)
    Item toEntity(ItemDto dto);

    ItemDto toDto(Item entity);
}
