import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import Login from './components/Login';
import Register from './components/Register';
import Profile from './components/Profile';
import ResetPassword from './components/ResetPassword';

function App() {
  const [token, setToken] = useState<string | null>(localStorage.getItem('token'));

  useEffect(() => {
    setToken(localStorage.getItem('token'));
  }, []);

  return (
    <Router>
      <Routes>
        {/* Ruta raíz: si hay token va a profile, si no a login */}
        <Route
          path="/"
          element={
            token ? <Navigate to="/profile" /> : <Navigate to="/login" />
          }
        />
        <Route
          path="/login"
          element={
            token ? <Navigate to="/profile" /> : <Login setToken={setToken} />
          }
        />
        <Route path="/register" element={<Register />} />
        <Route
          path="/profile"
          element={
            token ? <Profile /> : <Navigate to="/login" />
          }
        />
        <Route path="/reset-password" element={<ResetPassword />} />
      </Routes>
    </Router>
  );
}

export default App;