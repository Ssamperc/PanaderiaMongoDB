package com.panaderia.panaderia.mappers;

import com.panaderia.panaderia.model.ProductoModel;
import com.panaderia.panaderia.model.ProductoModelV2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    com.panaderia.panaderia.entity.ProductoEntity modelToEntity(ProductoModel productoModel);

    ProductoModel entityToModel(com.panaderia.panaderia.entity.ProductoEntity producto);

    ProductoModelV2 entityToModelV2(com.panaderia.panaderia.entity.ProductoEntity producto);

    com.panaderia.panaderia.entity.ProductoEntity modelV2toEntity(ProductoModelV2 productoModelV2);
}