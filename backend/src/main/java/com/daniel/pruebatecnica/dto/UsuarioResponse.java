package com.daniel.pruebatecnica.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioResponse {
    private Long id;
    private String nombreCompleto;
    private String email;
    private boolean activo;
    private LocalDateTime fechaCreacion;
}