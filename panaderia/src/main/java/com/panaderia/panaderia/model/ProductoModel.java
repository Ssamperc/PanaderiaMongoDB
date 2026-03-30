package com.panaderia.panaderia.model;

import com.panaderia.panaderia.entity.CategoriaEntity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProductoModel {

    private Long id;
    private String nombre;
    private Integer cantidad;
    private Integer existencia;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;
    private CategoriaEntity categoria;
}
