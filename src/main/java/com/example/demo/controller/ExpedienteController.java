package com.example.demo.controller;

import com.example.demo.model.Expediente;
import com.example.demo.service.ExpedienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expedientes")
@CrossOrigin(origins = "*") 
public class ExpedienteController {

    @Autowired
    private ExpedienteService service;

    @GetMapping
    public List<Expediente> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expediente> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Expediente crear(@RequestBody Expediente expediente) {
        return service.guardar(expediente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expediente> actualizar(@PathVariable Long id, @RequestBody Expediente expediente) {
        return service.obtenerPorId(id).map(existente -> {
            expediente.setId(existente.getId());
            return ResponseEntity.ok(service.guardar(expediente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}