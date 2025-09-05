import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import RegistrationPage from "./pages/RegistrationPage";
import UpdatePage from "./pages/UpdatePage";
import LoginPage from "./pages/LoginPage";
import DeletePage from "./pages/DeletePage";
import AdminLoginPage from "./AdminLoginPage";
import AdminDashboard from "./Admindashboard";

function App() 
{
  return (
    <Router>
      <nav style={{ padding: "10px", background: "#ddd" }}>
        <Link to="/" style={{ marginRight: "10px" }}>Login</Link>
        <Link to="/register" style={{ marginRight: "10px" }}>Register</Link>
        <Link to="/update">Update</Link>
        <Link to="/delete" style={{ marginLeft: "10px" }}>Delete</Link>
        <Link to="/admin" style={{ marginLeft: "10px" }}>Admin Login</Link>
      </nav>
      <Routes>
        <Route path="/admin" element={<AdminLoginPage />} />
        <Route path="/admin/dashboard" element={<AdminDashboard />} />
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegistrationPage />} />
        <Route path="/update" element={<UpdatePage />} />
        <Route path="/delete" element={<DeletePage />} />
        <Route path="*" element={<LoginPage />} />
      </Routes>
    </Router>
  );
}

export default App;
