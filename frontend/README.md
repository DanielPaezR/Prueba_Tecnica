# Sistema de Autenticación - Frontend

Interfaz de usuario desarrollada con React, TypeScript y Tailwind CSS.

## 🚀 Tecnologías
- React 18 + TypeScript
- Vite
- Tailwind CSS
- Axios
- React Router DOM

## 📋 Requisitos
- Node.js 18+
- npm

## 🛠️ Instalación y ejecución

npm install
npm run dev

El frontend estará disponible en http://localhost:5173

## 🔗 Backend requerido
El frontend consume la API del backend que debe estar corriendo en http://localhost:8081

## 📁 Estructura del proyecto

frontend/
├── src/
│   ├── components/
│   │   ├── Login.tsx
│   │   ├── Register.tsx
│   │   ├── Profile.tsx
│   │   ├── Navbar.tsx
│   │   └── ResetPassword.tsx
│   ├── services/
│   │   └── api.ts
│   ├── App.tsx
│   ├── main.tsx
│   └── index.css
├── index.html
├── tailwind.config.js
├── tsconfig.json
└── package.json

## 🧪 Flujo de uso

1. Registro: Ir a /register y completar el formulario
2. Confirmación: Copiar el token de la consola del backend y visitar:
   http://localhost:8081/api/auth/confirmar?token=TOKEN
3. Login: Ir a /login con las credenciales
4. Perfil: Ver información personal del usuario

## 🎨 Estilos
Se utiliza Tailwind CSS. Configuración en tailwind.config.js

## 🔧 Configuración de API
La URL base está en src/services/api.ts:
const API_BASE_URL = 'http://localhost:8081/api'