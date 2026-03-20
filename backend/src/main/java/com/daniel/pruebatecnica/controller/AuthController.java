package com.daniel.pruebatecnica.controller;

import com.daniel.pruebatecnica.dto.*;
import com.daniel.pruebatecnica.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints para manejo de sesión y autenticación")
public class AuthController {
    
    private final UsuarioService usuarioService;
    
    @PostMapping("/registro")
    @Operation(summary = "Registrar nuevo usuario")
    public ResponseEntity<UsuarioResponse> registrar(
            @Valid @RequestBody RegistroRequest request
    ) {
        return ResponseEntity.ok(usuarioService.registrar(request));
    }
    
    @GetMapping("/confirmar")
    @Operation(summary = "Confirmar email con token")
    public ResponseEntity<String> confirmarEmail(
            @RequestParam String token
    ) {
        return ResponseEntity.ok(usuarioService.confirmarEmail(token));
    }
    
    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(usuarioService.login(request));
    }
    
    @PostMapping("/recuperar-solicitud")
    @Operation(summary = "Solicitar recuperación de contraseña")
    public ResponseEntity<String> solicitarRecuperacion(
            @RequestParam String email
    ) {
        return ResponseEntity.ok(usuarioService.solicitarRecuperacionPassword(email));
    }
    
    @PostMapping("/recuperar-cambiar")
    @Operation(summary = "Cambiar contraseña con token")
    public ResponseEntity<String> cambiarPasswordConToken(
            @RequestParam String token,
            @Valid @RequestBody CambioPasswordRequest request
    ) {
        return ResponseEntity.ok(
            usuarioService.cambiarPasswordConToken(token, request.getNuevaPassword())
        );
    }
}