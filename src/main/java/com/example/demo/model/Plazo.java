package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "plazos")
public class Plazo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private LocalDate fechaVencimiento;
    private String responsable;
    private boolean cumplido = false;

    private Long expedienteId; // Referencia simple al expediente para no complicar el JSON
}