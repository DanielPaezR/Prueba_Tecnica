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

## 📋 Requisitos
- Java 17
- PostgreSQL (o MySQL)
- Maven

## 🛠️ Configuración

### 1. Crear la base de datos
```sql
CREATE DATABASE auth_db;