package com.example.demo.repository;

import com.example.demo.model.Expediente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Faltaba esta importación para que reconozca la palabra List

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
    
    List<Expediente> findByNumeroInternoContainingOrClienteRutContaining(String numero, String rut);

}