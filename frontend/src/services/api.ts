import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para agregar el token a todas las peticiones
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Tipos
export interface RegisterData {
  nombreCompleto: string;
  email: string;
  password: string;
}

export interface LoginData {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  tipo: string;
  email: string;
  nombre: string;
}

export interface UsuarioResponse {
  id: number;
  nombreCompleto: string;
  email: string;
  activo: boolean;
  fechaCreacion: string;
}

// Servicios de autenticación
export const authService = {
  register: (data: RegisterData) => api.post('/auth/registro', data),
  confirmEmail: (token: string) => api.get(`/auth/confirmar?token=${token}`),
  login: (data: LoginData) => api.post<LoginResponse>('/auth/login', data),
  requestPasswordReset: (email: string) => api.post(`/auth/recuperar-solicitud?email=${email}`),
  resetPassword: (token: string, nuevaPassword: string) => 
    api.post(`/auth/recuperar-cambiar?token=${token}`, { nuevaPassword }),
};

// Servicios de usuario
export const userService = {
  getProfile: () => api.get<UsuarioResponse>('/usuarios/perfil'),
  getAllUsers: () => api.get<UsuarioResponse[]>('/usuarios'),
  getUserById: (id: number) => api.get<UsuarioResponse>(`/usuarios/${id}`),
};

export default api;