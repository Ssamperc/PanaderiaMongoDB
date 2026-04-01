package com.panaderia.panaderia.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoEntity {

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("cantidad")
    private String cantidad;

    @Field("existencia")
    private Integer existencia;

    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Field("fecha_edicion")
    private LocalDateTime fechaEdicion;

    @Field("categoria_id")
    private String categoriaId;
}