package com.panaderia.panaderia.mappers;

import com.panaderia.panaderia.entity.ProductoEntity;
import com.panaderia.panaderia.model.ProductoModel;
import com.panaderia.panaderia.model.ProductoModelV2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoModel entityToModel(ProductoEntity entity);

    ProductoEntity modelToEntity(ProductoModel model);

    ProductoModelV2 entityToModelV2(ProductoEntity entity);

    ProductoEntity modelV2toEntity(ProductoModelV2 model);
}