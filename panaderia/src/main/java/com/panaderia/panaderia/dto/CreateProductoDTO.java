package com.panaderia.panaderia.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductoDTO {

    private String nombre;
    private String cantidad;
    private Integer existencia;
    private String categoriaId;
}