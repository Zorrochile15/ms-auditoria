package com.example.demo.service;

import com.example.demo.model.Expediente;
import com.example.demo.repository.ExpedienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpedienteService {

    @Autowired
    private ExpedienteRepository repository;

    public List<Expediente> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Expediente> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Expediente guardar(Expediente expediente) {
        return repository.save(expediente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}