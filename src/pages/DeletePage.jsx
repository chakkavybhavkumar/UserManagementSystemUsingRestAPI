import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./DeletePage.css"; // Create this CSS file

export default function DeletePage() {
  const [emailid, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleDelete = (e) => {
    e.preventDefault();

    axios
      .delete("http://localhost:8080/delete", {
        data: { useremailid: emailid }, // sending email in request body
      })
      .then((res) => {
        setMessage(res.data);

        // Redirect to login after 1.5 seconds
        setTimeout(() => {
          navigate("/");
        }, 1500);
      })
      .catch((err) => {
        setMessage(
          err.response ? err.response.data : "Error deleting account"
        );
      });
  };

  return (
    <div className="delete-container">
      <h2>Delete Account</h2>
      <form onSubmit={handleDelete} className="delete-form">
        <input
          type="email"
          placeholder="Enter your Email to confirm"
          value={emailid}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <button type="submit" className="delete-btn">
          Delete Account
        </button>
      </form>
      <p className="message">{message}</p>
    </div>
  );
}
