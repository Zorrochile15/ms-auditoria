package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UsuarioRepository repository; // Simulamos la base de datos

    @InjectMocks
    private AuthService service; // Inyectamos el mock en el servicio

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ConCredencialesCorrectas_DeberiaRetornarUsuario() {
        // 1. Preparar los datos falsos (Mock)
        Usuario mockUser = new Usuario();
        mockUser.setUsername("abogado1");
        mockUser.setPassword("1234");
        mockUser.setRol("ABOGADO");

        // Le decimos a Mockito: "Cuando el servicio busque a 'abogado1' con '1234', devuélveme este mockUser"
        when(repository.findByUsernameAndPassword("abogado1", "1234")).thenReturn(Optional.of(mockUser));

        // 2. Ejecutar el método real
        Optional<Usuario> resultado = service.login("abogado1", "1234");

        // 3. Verificar que funcione (Assertions)
        assertTrue(resultado.isPresent());
        assertEquals("ABOGADO", resultado.get().getRol());
        verify(repository, times(1)).findByUsernameAndPassword("abogado1", "1234"); // Verifica que se llamó a BD
    }

    @Test
    void login_ConCredencialesIncorrectas_DeberiaRetornarVacio() {
        when(repository.findByUsernameAndPassword("admin", "malapass")).thenReturn(Optional.empty());

        Optional<Usuario> resultado = service.login("admin", "malapass");

        assertFalse(resultado.isPresent());
    }
}