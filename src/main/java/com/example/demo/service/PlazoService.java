package com.example.demo.service;

import com.example.demo.model.Plazo;
import com.example.demo.repository.PlazoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlazoService {

    @Autowired
    private PlazoRepository repository;

    public List<Plazo> obtenerPorExpediente(Long expedienteId) {
        return repository.findByExpedienteId(expedienteId);
    }

    public Plazo guardar(Plazo plazo) {
        return repository.save(plazo);
    }

    public Plazo marcarComoCumplido(Long id) {
        Plazo plazo = repository.findById(id).orElseThrow(() -> new RuntimeException("Plazo no encontrado"));
        plazo.setCumplido(true);
        return repository.save(plazo);
    }

    public List<Plazo> obtenerAlertas() {
        LocalDate hoy = LocalDate.now();
        LocalDate enTresDias = hoy.plusDays(3);
        return repository.findByFechaVencimientoBetweenAndCumplidoFalse(hoy, enTresDias);
    }
}