package com.example.demo.service;

import com.example.demo.model.Expediente;
import com.example.demo.repository.ExpedienteRepository;
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

class ExpedienteServiceTest {

    @Mock
    private ExpedienteRepository repository;

    @InjectMocks
    private ExpedienteService service;

    private Expediente expediente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        expediente = new Expediente();
        expediente.setId(1L);
        expediente.setNumeroInterno("EXP-123");
        expediente.setClienteRut("11111111-1");
        expediente.setClienteNombre("Juan Perez");
        expediente.setMateria("Civil");
        expediente.setJuzgado("1er Juzgado");
        expediente.setFechaIngreso(LocalDate.now());
    }

    @Test
    void obtenerTodos_DeberiaRetornarLista() {
        when(repository.findAll()).thenReturn(Arrays.asList(expediente));
        List<Expediente> resultado = service.obtenerTodos();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }

    @Test
    void obtenerPorId_DeberiaRetornarExpediente() {
        when(repository.findById(1L)).thenReturn(Optional.of(expediente));
        Optional<Expediente> resultado = service.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals("EXP-123", resultado.get().getNumeroInterno());
    }

    @Test
    void guardar_DeberiaRetornarExpedienteGuardado() {
        when(repository.save(any(Expediente.class))).thenReturn(expediente);
        Expediente resultado = service.guardar(new Expediente());
        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getClienteNombre());
    }

    @Test
    void eliminar_DeberiaLlamarAlRepositorio() {
        doNothing().when(repository).deleteById(1L);
        service.eliminar(1L);
        verify(repository, times(1)).deleteById(1L); // Verifica que el método delete se llamó 1 vez
    }
}