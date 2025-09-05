// import React, { useState } from "react";
// import axios from "axios";
// import { useNavigate, Link } from "react-router-dom";

// export default function LoginPage() {
//   const [email, setEmail] = useState("");
//   const [password, setPassword] = useState("");
//   const [message, setMessage] = useState("");
//   const navigate = useNavigate();

//   const handleLogin = (e) => {
//     e.preventDefault();
//     axios.post("http://localhost:8080/login", {
//       useremailid: email,
//       password: password
//     })
//     .then(res => {
//       setMessage(res.data);
//       navigate("/update");
//     })
//     .catch(err => {
//       setMessage(err.response ? err.response.data : "Error logging in");
//     });
//   };

//   return (
//     <div style={{ padding: "20px" }}>
//       <h2>Login</h2>
//       <form onSubmit={handleLogin}>
//         <input placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} /><br/>
//         <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} /><br/>
//         <button type="submit">Login</button>
//       </form>
//       <p>{message}</p>
//       <p>Not registered? <Link to="/register">Click here</Link></p>
//     </div>
//   );
// }
import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import "./LoginPage.css"; // Import CSS file

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/login", {
        useremailid: email,
        password: password,
      })
      .then((res) => {
        setMessage(res.data);
        navigate("/update");
      })
      .catch((err) => {
        setMessage(err.response ? err.response.data : "Error logging in");
      });
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin} className="login-form">
        <input
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Login</button>
      </form>
      <p className="message">{message}</p>
      <p className="register-link">
        Not registered? <Link to="/register">Click here</Link>
      </p>
    </div>
  );
}


