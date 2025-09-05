import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import "./AdminLoginPage.css"; // custom CSS file

export default function AdminLoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  // Handle login
  const handleLogin = async (e) => {
    e.preventDefault();
    setMessage("");
    try {
      const response = await axios.post("http://localhost:8080/admin/login", {
        email,
        password,
      });
      setMessage("Login successful!");
      navigate("/admin/dashboard"); // go to dashboard after login
    } catch (error) {
      setMessage("Invalid admin credentials");
    }
  };

  return (
    <div className="login-container">
      <h2>Admin Login</h2>

      {message && <p className="login-message">{message}</p>}

      <form onSubmit={handleLogin} className="login-form">
        <label>Email</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <label>Password</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        <button type="submit" className="login-btn">
          Login
        </button>
      </form>

      <div className="back-link">
        <Link to="/">⬅ Back to User Login</Link>
      </div>
    </div>
  );
}
