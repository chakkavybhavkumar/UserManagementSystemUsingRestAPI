import React, { useEffect, useState } from "react";
import axios from "axios";
import "./AdminDashboard.css"; 

export default function AdminDashboard() {
  const [users, setUsers] = useState([]);
  const [message, setMessage] = useState("");
  const [editingUser, setEditingUser] = useState(null);

  const loadUsers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/admin/users");
      setUsers(response.data);
    } catch (error) {
      setMessage("Failed to load users.");
    }
  };

  useEffect(() => {
    loadUsers();
  }, []);

  // Delete a user
  const deleteUser = async (id) => {
    if (window.confirm("Are you sure you want to delete this user?")) {
      try {
        await axios.delete(`http://localhost:8080/admin/users/${id}`);
        setMessage("User deleted successfully.");
        loadUsers();
      } catch (error) {
        setMessage("Failed to delete user.");
      }
    }
  };

  const saveUser = async () => {
    try {
      await axios.put(
        `http://localhost:8080/admin/users/${editingUser.id}`,
        editingUser
      );
      setMessage("User updated successfully.");
      setEditingUser(null);
      loadUsers();
    } catch (error) {
      setMessage("Failed to update user.");
    }
  };

  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditingUser({ ...editingUser, [name]: value });
  };

  return (
    <div className="admin-container">
      <h2>Admin Dashboard</h2>
      <button className="refresh-btn" onClick={loadUsers}>
        Refresh
      </button>

      {message && <p className="message">{message}</p>}

    
      <table className="user-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Gender</th>
            <th>Password</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.length > 0 ? (
            users.map((u) => (
              <tr key={u.id}>
                <td>{u.id}</td>
                <td>{u.username}</td>
                <td>{u.useremailid}</td>
                <td>{u.mobilenumber}</td>
                <td>{u.gender}</td>
                <td>{u.password}</td>
                <td>
                  <button
                    className="edit-btn"
                    onClick={() => setEditingUser(u)}
                  >
                    Edit
                  </button>
                  <button
                    className="delete-btn"
                    onClick={() => deleteUser(u.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7" style={{ textAlign: "center" }}>
                No users found.
              </td>
            </tr>
          )}
        </tbody>
      </table>


      {editingUser && (
        <div className="edit-form">
          <h3>Edit User</h3>
          <input
            type="text"
            name="username"
            value={editingUser.username}
            onChange={handleChange}
            placeholder="Username"
          />
          <input
            type="email"
            name="useremailid"
            value={editingUser.useremailid}
            onChange={handleChange}
            placeholder="Email"
          />
          <input
            type="number"
            name="mobilenumber"
            value={editingUser.mobilenumber}
            onChange={handleChange}
            placeholder="Mobile"
          />
          <select
            name="gender"
            value={editingUser.gender}
            onChange={handleChange}
          >
            <option value="">--Select Gender--</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
          <input
            type="text"
            name="password"
            value={editingUser.password}
            onChange={handleChange}
            placeholder="Password"
          />
          <div className="form-actions">
            <button className="save-btn" onClick={saveUser}>
              Save
            </button>
            <button className="cancel-btn" onClick={() => setEditingUser(null)}>
              Cancel
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
