package com.panaderia.panaderia.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Asesor")
@Data
public class AsesorEntity {

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("cc")
    private String cc;

    @Field("age")
    private Integer age;

    @Field("evidence")
    private String evidence;
}