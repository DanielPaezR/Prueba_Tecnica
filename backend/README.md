# Sistema de Autenticación con Spring Boot

Sistema completo de autenticación que incluye registro de usuarios, confirmación por email, login con JWT y recuperación de contraseña.

## 🚀 Características
- Registro de usuarios con confirmación por email
- Token único de confirmación (UUID) de un solo uso
- Login con JWT (JSON Web Token)
- Recuperación de contraseña con token temporal
- Validación de usuario activo antes de login
- Documentación automática con Swagger/OpenAPI
- Arquitectura MVC con separación de controladores
- CORS configurado para frontend (http://localhost:5173)

## 📋 Requisitos
- Java 17
- PostgreSQL
- Maven

## 🛠️ Configuración para probar

### 1. Crear la base de datos
CREATE DATABASE auth_db;

### 2. Configurar credenciales en `src/main/resources/application.properties`
spring.datasource.url=jdbc:postgresql://localhost:5432/auth_db
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña

### 3. Ejecutar la aplicación
mvn spring-boot:run

La API estará disponible en http://localhost:8081

## 📚 Documentación
Swagger UI: http://localhost:8081/swagger-ui.html

## 🔑 Endpoints principales

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | /api/auth/registro | Registrar nuevo usuario | No |
| GET | /api/auth/confirmar | Confirmar email con token | No |
| POST | /api/auth/login | Iniciar sesión (retorna JWT) | No |
| POST | /api/auth/recuperar-solicitud | Solicitar recuperación | No |
| POST | /api/auth/recuperar-cambiar | Cambiar contraseña con token | No |
| GET | /api/usuarios/perfil | Obtener perfil del usuario | Sí (JWT) |
| GET | /api/usuarios | Listar todos los usuarios | Sí (JWT) |

## 📝 Nota sobre emails
Los tokens de confirmación y recuperación se muestran en la consola del backend:

ENLACE DE CONFIRMACIÓN:
http://localhost:8081/api/auth/confirmar?token=abc-123-def-456

## 📁 Estructura del proyecto
backend/
├── src/main/java/com/daniel/pruebatecnica/
│   ├── config/          # Configuración (Security, CORS)
│   ├── controller/      # Controladores REST
│   ├── dto/             # Objetos de transferencia
│   ├── model/           # Entidad Usuario
│   ├── repository/      # Repositorio JPA
│   ├── security/        # JWT y filtros
│   └── service/         # Lógica de negocio
└── src/main/resources/
    └── application.properties

