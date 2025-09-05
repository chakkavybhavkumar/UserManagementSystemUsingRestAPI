import React, { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Registration.css"; 
import { useNavigate } from "react-router-dom"; 

const RegistrationPage = () => {
  const [formData, setFormData] = useState({
    username: "",
    useremailid: "",
    mobilenumber: "",
    password: "",
    gender: "",
  });

  const [message, setMessage] = useState("");
  const navigate = useNavigate(); 

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");

    try {
      const res = await axios.post("/registration", formData);
      if (res.status === 201) {
        setMessage("Registration successful!");
        setFormData({
          username: "",
          useremailid: "",
          mobilenumber: "",
          password: "",
          gender: "",
        });

      
        setTimeout(() => {
          navigate("/");
        }, 1500);
      }
    } catch (error) {
      if (error.response && error.response.data) {
        const errors = error.response.data;
        let errMsg = "";
        for (let key in errors) {
          errMsg += `${key}: ${errors[key]}\n`;
        }
        setMessage(errMsg);
      } else {
        setMessage("Server error. Please try again later.");
      }
    }
  };

  return (
    <div className="registration-container">
      <h2 className="registration-title">User Registration</h2>
      {message && (
        <div className="alert alert-info">
          <pre>{message}</pre>
        </div>
      )}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Username</label>
          <input
            type="text"
            name="username"
            className="form-control"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Email</label>
          <input
            type="email"
            name="useremailid"
            className="form-control"
            value={formData.useremailid}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Mobile Number</label>
          <input
            type="number"
            name="mobilenumber"
            className="form-control"
            value={formData.mobilenumber}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Password</label>
          <input
            type="password"
            name="password"
            className="form-control"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Gender</label>
          <select
            name="gender"
            className="form-control"
            value={formData.gender}
            onChange={handleChange}
            required
          >
            <option value="">-- Select Gender --</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
        </div>
        <button className="btn btn-primary w-100" type="submit">
          Register
        </button>
      </form>
    </div>
  );
};

export default RegistrationPage;
