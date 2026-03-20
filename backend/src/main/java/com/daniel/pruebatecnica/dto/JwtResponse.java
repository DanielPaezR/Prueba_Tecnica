package com.daniel.pruebatecnica.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    private String token;
    private String tipo;
    private String email;
    private String nombre;
}