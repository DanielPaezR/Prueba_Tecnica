package com.daniel.pruebatecnica.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    
    private final JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    @Value("${app.base-url}")
    private String baseUrl;
    
    public void enviarCorreoConfirmacion(String to, String nombre, String token) {
        // Esto se imprime en la consola
        System.out.println("========================================");
        System.out.println("ENLACE DE CONFIRMACIÓN (copia esto en tu navegador):");
        System.out.println(baseUrl + "/api/auth/confirmar?token=" + token);
        System.out.println("========================================");
        
        String asunto = "Confirma tu cuenta";
        String contenido = String.format(
                "Hola %s,\n\n" +
                "Por favor confirma tu cuenta haciendo clic en el siguiente enlace:\n" +
                "%s/api/auth/confirmar?token=%s\n\n" +
                "Este enlace es válido por 24 horas.\n\n" +
                "Saludos,\nEl equipo",
                nombre, baseUrl, token
        );
        
        enviarEmail(to, asunto, contenido);
    }
    
    public void enviarCorreoRecuperacion(String to, String nombre, String token) {
        System.out.println("========================================");
        System.out.println("ENLACE DE RECUPERACIÓN (copia esto):");
        System.out.println(baseUrl + "/api/auth/recuperar-password?token=" + token);
        System.out.println("========================================");
        
        String asunto = "Recuperación de contraseña";
        String contenido = String.format(
                "Hola %s,\n\n" +
                "Has solicitado recuperar tu contraseña. Haz clic en el siguiente enlace:\n" +
                "%s/api/auth/recuperar-password?token=%s\n\n" +
                "Si no solicitaste esto, ignora este correo.\n\n" +
                "Saludos,\nEl equipo",
                nombre, baseUrl, token
        );
        
        enviarEmail(to, asunto, contenido);
    }
    
    private void enviarEmail(String to, String asunto, String contenido) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(asunto);
            message.setText(contenido);
            
            mailSender.send(message);
            log.info("Correo enviado a: {}", to);
        } catch (Exception e) {
            log.error("Error enviando correo a {}: {}", to, e.getMessage());
            // No lanzamos excepción para no interrumpir el flujo principal
        }
    }
}