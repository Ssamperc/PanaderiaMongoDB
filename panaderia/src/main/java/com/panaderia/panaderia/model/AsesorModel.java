package com.panaderia.panaderia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsesorModel {

    private String id;
    private String nombre;
    private String cc;
    private Integer age;
    private String evidence;
}