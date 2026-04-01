package com.panaderia.panaderia.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoModel {

    private String id;
    private String nombre;
    private String cantidad;
    private Integer existencia;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEdicion;
    private String categoriaId;
}