package com.example.demo.repository;

import com.example.demo.model.Expediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Faltaba esta importación para que reconozca la palabra List

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
    
    List<Expediente> findByNumeroInternoContainingOrClienteRutContaining(String numero, String rut);

}