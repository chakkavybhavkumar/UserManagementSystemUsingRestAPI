import React, { useState } from "react";
import axios from "axios";
import "./UpdatePage.css"; // Import CSS file
import { useNavigate } from "react-router-dom"; 

export default function UpdatePage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate(); 

  const handleUpdate = (e) => {
    e.preventDefault();
    axios
      .put("http://localhost:8080/update", {
        useremailid: email,
        password: password,
        username: name,
        phone: phone,
        address: address,
      })
      .then((res) => {
        setMessage(res.data);

        setTimeout(() => {
          navigate("/");
        }, 1500);
      })
      .catch(() => setMessage("Update failed"));
  };

  return (
    <div className="update-container">
      <h2>Update User Details</h2>
      <form onSubmit={handleUpdate} className="update-form">
        <input
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="New Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input
          placeholder="Full Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          placeholder="Phone Number"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
        />
        <input
          placeholder="Address"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
        />
        <button type="submit">Update</button>
      </form>
      <p className="message">{message}</p>
    </div>
  );
}
