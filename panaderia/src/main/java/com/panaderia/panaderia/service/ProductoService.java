package com.panaderia.panaderia.service;

import com.panaderia.panaderia.dto.CreateProductoDTO;
import com.panaderia.panaderia.model.ProductoModel;
import com.panaderia.panaderia.model.ProductoModelV2;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductoService {


    ProductoModel createProducto(CreateProductoDTO createProductoDTO);

    ProductoModelV2 createProductoV2(CreateProductoDTO createProductoDTO);

    ProductoModelV2 getProductoById(Long id);

    Page<ProductoModel> getAllProductos(int page, int size);

    List<ProductoModelV2> getAllProductosV2();

    Page<ProductoModelV2> getAllProductosV2(int page, int size, String nombre);

}
