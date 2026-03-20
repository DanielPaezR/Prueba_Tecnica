# Sistema de Autenticación - Prueba Técnica

Sistema completo de autenticación con **Spring Boot** en el backend y **React + TypeScript** en el frontend. Incluye registro de usuarios, confirmación por email, login con JWT, recuperación de contraseña y documentación con Swagger.

## 🚀 Tecnologías

### Backend
- **Java 17** + Spring Boot 3.1.5
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- Swagger / OpenAPI
- Maven

### Frontend
- **React 18** + TypeScript
- Vite
- Tailwind CSS
- Axios
- React Router DOM

## 📋 Requisitos previos

- Java 17
- Node.js 18+ y npm
- PostgreSQL
- Maven

## 🛠️ Configuración e instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/DanielPaezR/Prueba_Tecnica.git
cd Prueba_Tecnica

Nota: El envío de emails está simulado en consola para facilitar la prueba. Para producción, solo es necesario configurar spring.mail.* en application.properties.