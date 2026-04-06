package com.canchas.api.service;

import com.canchas.api.dto.*;
import com.canchas.api.entity.Usuario;
import com.canchas.api.repository.UsuarioRepository;
import com.canchas.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        usuarioRepository.save(usuario);
        String token = jwtUtil.generateToken(usuario.getCorreo());
        return new AuthResponse(token, usuario.getNombre(), usuario.getCorreo());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(), request.getPassword()));

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow();

        String token = jwtUtil.generateToken(usuario.getCorreo());
        return new AuthResponse(token, usuario.getNombre(), usuario.getCorreo());
    }
}