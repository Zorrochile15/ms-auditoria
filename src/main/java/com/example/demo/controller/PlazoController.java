package com.example.demo.controller;

import com.example.demo.model.Plazo;
import com.example.demo.repository.PlazoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/plazos")
@CrossOrigin(origins = "*")
public class PlazoController {

    @Autowired
    private PlazoRepository repository;

    @GetMapping("/expediente/{expedienteId}")
    public List<Plazo> listarPorExpediente(@PathVariable Long expedienteId) {
        return repository.findByExpedienteId(expedienteId);
    }

    @PostMapping
    public Plazo crear(@RequestBody Plazo plazo) {
        return repository.save(plazo);
    }

    @PutMapping("/{id}/cumplir")
    public Plazo marcarCumplido(@PathVariable Long id) {
        Plazo plazo = repository.findById(id).orElseThrow();
        plazo.setCumplido(true);
        return repository.save(plazo);
    }

    // Endpoint para la alerta de 3 días que consumirá el frontend
    @GetMapping("/alertas")
    public List<Plazo> obtenerAlertas() {
        LocalDate hoy = LocalDate.now();
        LocalDate enTresDias = hoy.plusDays(3);
        return repository.findByFechaVencimientoBetweenAndCumplidoFalse(hoy, enTresDias);
    }
}