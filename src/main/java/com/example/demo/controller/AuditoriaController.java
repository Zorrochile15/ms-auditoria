package com.example.demo.controller;

import com.example.demo.model.Auditoria;
import com.example.demo.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@CrossOrigin(origins = "*")
public class AuditoriaController {

    @Autowired
    private AuditoriaRepository repository;

    @PostMapping
    public Auditoria registrarActividad(@RequestBody Auditoria auditoria) {
        return repository.save(auditoria);
    }

    @GetMapping("/expediente/{expedienteId}")
    public List<Auditoria> obtenerTimeline(@PathVariable Long expedienteId) {
        return repository.findByExpedienteIdOrderByFechaDesc(expedienteId);
    }
}