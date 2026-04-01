package com.panaderia.panaderia.mappers;

import com.panaderia.panaderia.entity.AsesorEntity;
import com.panaderia.panaderia.model.AsesorModel;
import com.panaderia.panaderia.model.AsesorModelV2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AsesorMapper {

    AsesorModel entityToModel(AsesorEntity entity);

    AsesorEntity modelToEntity(AsesorModel model);

    AsesorModelV2 entityToModelV2(AsesorEntity entity);

    @Mapping(target = "cc", ignore = true)
    @Mapping(target = "age", ignore = true)
    AsesorEntity modelV2ToEntity(AsesorModelV2 model);
}