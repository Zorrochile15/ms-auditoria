package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long expedienteId;
    private String usuarioResponsable;
    private String accion; // Ej: "Modificó el juzgado", "Creó el expediente"
    private LocalDateTime fecha = LocalDateTime.now();
}