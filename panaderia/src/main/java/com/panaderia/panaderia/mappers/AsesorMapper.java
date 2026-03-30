package com.panaderia.panaderia.mappers;

import com.panaderia.panaderia.entity.AsesorEntity;
import com.panaderia.panaderia.model.AsesorModel;
import com.panaderia.panaderia.model.AsesorModelV2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AsesorMapper {

    AsesorEntity modelToEntity(AsesorModel asesorModel);

    AsesorModel entityToModel(AsesorEntity asesor);

    AsesorModelV2 entityToModelV2(AsesorEntity asesor);

    AsesorEntity modelV2toEntity(AsesorModelV2 asesorModelV2);
}