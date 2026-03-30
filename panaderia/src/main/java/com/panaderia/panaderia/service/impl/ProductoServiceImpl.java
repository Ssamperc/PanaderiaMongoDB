package com.panaderia.panaderia.service.impl;


import com.panaderia.panaderia.dto.CreateProductoDTO;
import com.panaderia.panaderia.entity.ProductoEntity;
import com.panaderia.panaderia.mappers.ProductoMapper;
import com.panaderia.panaderia.model.ProductoModel;
import com.panaderia.panaderia.model.ProductoModelV2;
import com.panaderia.panaderia.repository.ProductoRepository;
import com.panaderia.panaderia.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoModel createProducto(CreateProductoDTO createProductoDTO) {
        ProductoModel productoModel = new ProductoModel();
        productoModel.setNombre(createProductoDTO.getNombre());
        productoModel.setCantidad(Integer.valueOf(createProductoDTO.getCantidad()));
        productoModel.setExistencia(createProductoDTO.getExistencia());
        productoModel.setFechaCreacion(LocalDateTime.from(LocalDate.now()));
        productoModel.setFechaEdicion(LocalDateTime.from(LocalDate.now()));

        ProductoEntity productoEntity = productoMapper.modelToEntity(productoModel);
        ProductoEntity savedProducto = productoRepository.save(productoEntity);

        ProductoModel response = productoMapper.entityToModel(savedProducto);
        response.setFechaCreacion(productoModel.getFechaCreacion());
        response.setFechaEdicion(productoModel.getFechaEdicion());

        return response;
    }

    @Override
    public ProductoModelV2 createProductoV2(CreateProductoDTO createProductoDTO) {
        ProductoModelV2 productoModel = new ProductoModelV2();
        productoModel.setNombre(createProductoDTO.getNombre());

        ProductoEntity productoEntity = productoMapper.modelV2toEntity(productoModel);
        ProductoEntity savedProducto = productoRepository.save(productoEntity);

        productoModel.setId(savedProducto.getId());
        return productoModel;
    }

    @Override
    public ProductoModelV2 getProductoById(Long id) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un producto con id: " + id));

        return productoMapper.entityToModelV2(producto);
    }

    @Override
    public Page<ProductoModel> getAllProductos(int page, int size) {
        PageRequest pagerequest = PageRequest.of(page, size);

        return productoRepository.findAll(pagerequest).map(productoMapper::entityToModel);
    }

    @Override
    public List<ProductoModelV2> getAllProductosV2() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::entityToModelV2)
                .toList();
    }

    @Override
    public Page<ProductoModelV2> getAllProductosV2(int page, int size, String nombre) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return productoRepository.findByNombreContainingIgnoreCase(nombre, pageRequest)
                .map(productoMapper::entityToModelV2);
    }

}