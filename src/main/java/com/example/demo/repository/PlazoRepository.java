package com.example.demo.repository;

import com.example.demo.model.Plazo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PlazoRepository extends JpaRepository<Plazo, Long> {
    List<Plazo> findByExpedienteId(Long expedienteId);
    List<Plazo> findByFechaVencimientoBetweenAndCumplidoFalse(LocalDate inicio, LocalDate fin);
}