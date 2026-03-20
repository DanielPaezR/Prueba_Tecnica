package com.daniel.pruebatecnica.repository;

import com.daniel.pruebatecnica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByTokenConfirmacion(String token);
    Optional<Usuario> findByTokenRecuperacion(String token);
    boolean existsByEmail(String email);
}