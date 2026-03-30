package com.panaderia.panaderia.controller;

import com.panaderia.panaderia.dto.CreateProductoDTO;
import com.panaderia.panaderia.model.ProductoModel;
import com.panaderia.panaderia.model.ProductoModelV2;
import com.panaderia.panaderia.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping(value = "/create")
    @Operation(summary = "Endpoint que se encarga de realizar la creacion de productos - V1")
    public ProductoModel create(@RequestBody CreateProductoDTO producto) {
        return productoService.createProducto(producto);
    }

    @PostMapping(value = "/create-v2")
    @Operation(summary = "Endpoint que se encarga de realizar la creacion de productos - V2")
    public ProductoModelV2 createV2(@RequestBody CreateProductoDTO producto) {
        return productoService.createProductoV2(producto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID y retornar solo ID y nombre")
    public ProductoModelV2 getById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @GetMapping("/page/{page}/{size}")
    @Operation(summary = "Buscar todos los productos")
    public Page<ProductoModel> getAllProductos(@PathVariable int page, @PathVariable int size) {
        return productoService.getAllProductos(page, size);
    }

    @GetMapping("/asesores/v2")
    @Operation(summary = "Mostrar todos los productos")
    public List<ProductoModelV2> getAllProductosV2() {
        return productoService.getAllProductosV2();
    }

    @GetMapping("/page/{page}/{size}/{nombre}")
    @Operation(summary = "Buscar productos paginados por nombre")
    public Page<ProductoModelV2> getAllProductosV2(@PathVariable int page, @PathVariable int size, @PathVariable String nombre) {
        return productoService.getAllProductosV2(page, size, nombre);
    }
}