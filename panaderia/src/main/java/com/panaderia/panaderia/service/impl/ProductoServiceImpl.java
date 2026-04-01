package com.panaderia.panaderia.service.impl;

import com.panaderia.panaderia.dto.CreateProductoDTO;
import com.panaderia.panaderia.entity.ProductoEntity;
import com.panaderia.panaderia.mappers.ProductoMapper;
import com.panaderia.panaderia.model.ProductoModel;
import com.panaderia.panaderia.model.ProductoModelV2;
import com.panaderia.panaderia.repository.ProductoRepository;
import com.panaderia.panaderia.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoModel createProducto(CreateProductoDTO createProductoDTO) {
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setId(UUID.randomUUID().toString());
        productoEntity.setNombre(createProductoDTO.getNombre());
        productoEntity.setCantidad(createProductoDTO.getCantidad());
        productoEntity.setExistencia(createProductoDTO.getExistencia());
        productoEntity.setCategoriaId(createProductoDTO.getCategoriaId());
        productoEntity.setFechaCreacion(LocalDateTime.now());
        productoEntity.setFechaEdicion(LocalDateTime.now());

        ProductoEntity saved = productoRepository.save(productoEntity);
        return productoMapper.entityToModel(saved);
    }

    @Override
    public ProductoModelV2 createProductoV2(CreateProductoDTO createProductoDTO) {
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setId(UUID.randomUUID().toString());
        productoEntity.setNombre(createProductoDTO.getNombre());
        productoEntity.setFechaCreacion(LocalDateTime.now());
        productoEntity.setFechaEdicion(LocalDateTime.now());

        ProductoEntity saved = productoRepository.save(productoEntity);
        return productoMapper.entityToModelV2(saved);
    }

    @Override
    public ProductoModelV2 getProductoById(String id) {
        ProductoEntity producto = productoRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("No existe un producto con id: " + id));

        return productoMapper.entityToModelV2(producto);
    }

    @Override
    public Page<ProductoModel> getAllProductos(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productoRepository.findAll(pageRequest).map(productoMapper::entityToModel);
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