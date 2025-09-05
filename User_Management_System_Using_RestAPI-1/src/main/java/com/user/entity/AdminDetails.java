package com.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AdminDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String useremailid;
    private long mobilenumber;
    private String gender;
    private String password;
    private String role;  // <-- add this (e.g., "USER" / "ADMIN")

    // getters & setters
}
