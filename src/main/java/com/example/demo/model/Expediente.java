package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "expedientes")
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroInterno;

    @Column(nullable = false)
    private String clienteRut;

    @Column(nullable = false)
    private String clienteNombre;

    @Column(nullable = false)
    private String materia;

    @Column(nullable = false)
    private String juzgado;

    @Column(nullable = false)
    private LocalDate fechaIngreso;
}