package com.example.demo.service;

import com.example.demo.model.Auditoria;
import com.example.demo.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository repository;

    public Auditoria registrarActividad(Auditoria auditoria) {
        return repository.save(auditoria);
    }

    public List<Auditoria> obtenerTimeline(Long expedienteId) {
        return repository.findByExpedienteIdOrderByFechaDesc(expedienteId);
    }
}