package com.daniel.pruebatecnica.service;

import com.daniel.pruebatecnica.dto.*;
import com.daniel.pruebatecnica.model.Usuario;
import com.daniel.pruebatecnica.repository.UsuarioRepository;
import com.daniel.pruebatecnica.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    
    @Transactional
    public UsuarioResponse registrar(RegistroRequest request) {
        // Validar si el email ya existe
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        // Crear token de confirmación (único)
        String tokenConfirmacion = UUID.randomUUID().toString();
        
        // Crear usuario (inactivo hasta confirmar email)
        Usuario usuario = Usuario.builder()
                .nombreCompleto(request.getNombreCompleto())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .tokenConfirmacion(tokenConfirmacion)
                .fechaExpiracionToken(LocalDateTime.now().plusHours(24))
                .activo(false)
                .build();
        
        usuarioRepository.save(usuario);
        
        // Enviar correo de confirmación
        emailService.enviarCorreoConfirmacion(
                usuario.getEmail(), 
                usuario.getNombreCompleto(), 
                tokenConfirmacion
        );
        
        return mapToResponse(usuario);
    }
    
    @Transactional
    public String confirmarEmail(String token) {
        Usuario usuario = usuarioRepository.findByTokenConfirmacion(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));
        
        // Validar si el token ya expiró
        if (usuario.getFechaExpiracionToken().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El token ha expirado");
        }
        
        // Activar usuario y limpiar token (un solo uso)
        usuario.setActivo(true);
        usuario.setTokenConfirmacion(null);
        usuario.setFechaExpiracionToken(null);
        
        usuarioRepository.save(usuario);
        
        return "Email confirmado exitosamente. Ya puedes iniciar sesión.";
    }
    
    public JwtResponse login(LoginRequest request) {
        // Autenticar usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        // Buscar usuario
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // Validar si está activo
        if (!usuario.isActivo()) {
            throw new RuntimeException("La cuenta no está activa. Por favor confirma tu email.");
        }
        
        // Generar token JWT
        String jwtToken = jwtService.generateToken(usuario);
        
        return JwtResponse.builder()
                .token(jwtToken)
                .tipo("Bearer")
                .email(usuario.getEmail())
                .nombre(usuario.getNombreCompleto())
                .build();
    }
    
    @Transactional
    public String solicitarRecuperacionPassword(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // Generar token de recuperación
        String tokenRecuperacion = UUID.randomUUID().toString();
        usuario.setTokenRecuperacion(tokenRecuperacion);
        usuario.setFechaExpiracionToken(LocalDateTime.now().plusHours(1)); // 1 hora para recuperar
        
        usuarioRepository.save(usuario);
        
        // Enviar correo
        emailService.enviarCorreoRecuperacion(
                usuario.getEmail(),
                usuario.getNombreCompleto(),
                tokenRecuperacion
        );
        
        return "Se ha enviado un enlace de recuperación a tu email";
    }
    
    @Transactional
    public String cambiarPasswordConToken(String token, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findByTokenRecuperacion(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));
        
        // Validar expiración
        if (usuario.getFechaExpiracionToken().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El token ha expirado");
        }
        
        // Cambiar contraseña
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuario.setTokenRecuperacion(null); // Un solo uso
        usuario.setFechaExpiracionToken(null);
        
        usuarioRepository.save(usuario);
        
        return "Contraseña actualizada exitosamente";
    }
    
    private UsuarioResponse mapToResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombreCompleto(usuario.getNombreCompleto())
                .email(usuario.getEmail())
                .activo(usuario.isActivo())
                .fechaCreacion(usuario.getFechaCreacion())
                .build();
    }
}