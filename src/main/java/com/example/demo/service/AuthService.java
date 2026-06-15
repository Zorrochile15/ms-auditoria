package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;

    public Optional<Usuario> login(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }
}