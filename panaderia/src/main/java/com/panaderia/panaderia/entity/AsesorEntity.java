package com.panaderia.panaderia.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "asesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsesorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String cc;
    private Integer age;


}
