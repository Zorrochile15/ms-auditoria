package com.example.demo.controller;

import com.example.demo.model.Plazo;
import com.example.demo.service.PlazoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/plazos")
@CrossOrigin(origins = "*")
public class PlazoController {

    @Autowired
    private PlazoService service;

    @GetMapping("/expediente/{expedienteId}")
    public List<Plazo> listarPorExpediente(@PathVariable Long expedienteId) {
        return service.obtenerPorExpediente(expedienteId);
    }

    @PostMapping
    public Plazo crear(@RequestBody Plazo plazo) {
        return service.guardar(plazo);
    }

    @PutMapping("/{id}/cumplir")
    public Plazo marcarCumplido(@PathVariable Long id) {
        return service.marcarComoCumplido(id);
    }

    @GetMapping("/alertas")
    public List<Plazo> obtenerAlertas() {
        return service.obtenerAlertas();
    }
}