package com.example.demo.service;

import com.example.demo.model.Plazo;
import com.example.demo.repository.PlazoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlazoServiceTest {

    @Mock
    private PlazoRepository repository;

    @InjectMocks
    private PlazoService service;

    private Plazo plazo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        plazo = new Plazo();
        plazo.setId(1L);
        plazo.setDescripcion("Presentar escrito");
        plazo.setFechaVencimiento(LocalDate.now().plusDays(2));
        plazo.setResponsable("Abogado 1");
        plazo.setCumplido(false); // Inicia como no cumplido
        plazo.setExpedienteId(1L);
    }

    @Test
    void obtenerPorExpediente_DeberiaRetornarLista() {
        when(repository.findByExpedienteId(1L)).thenReturn(Arrays.asList(plazo));
        List<Plazo> resultado = service.obtenerPorExpediente(1L);
        assertFalse(resultado.isEmpty());
        assertEquals(1L, resultado.get(0).getExpedienteId());
    }

    @Test
    void marcarComoCumplido_DeberiaActualizarEstado() {
        when(repository.findById(1L)).thenReturn(Optional.of(plazo));
        when(repository.save(any(Plazo.class))).thenReturn(plazo);
        
        Plazo resultado = service.marcarComoCumplido(1L);
        
        assertTrue(resultado.isCumplido()); // Verifica que cambió a true
        verify(repository, times(1)).save(plazo); // Verifica que se guardó el cambio
    }

    @Test
    void obtenerAlertas_DeberiaRetornarPlazosProximos() {
        when(repository.findByFechaVencimientoBetweenAndCumplidoFalse(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Arrays.asList(plazo));
                
        List<Plazo> resultado = service.obtenerAlertas();
        
        assertFalse(resultado.isEmpty());
        assertEquals("Presentar escrito", resultado.get(0).getDescripcion());
    }
}