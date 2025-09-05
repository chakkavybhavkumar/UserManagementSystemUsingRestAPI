package com.user.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.Repository.UserRepository;
import com.user.entity.UserDetails;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateAdmin(String email, String password) {
        return "vybhavkumar2003@gmail.com".equalsIgnoreCase(email) && "admin123".equals(password);
    }
    
//    public boolean validateAdmin(String email, String password) {
//        return userRepository.findByUseremailidAndPassword(email, password)
//                .map(user -> "ADMIN".equalsIgnoreCase(user.getRole()))
//                .orElse(false);
//    }

    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDetails> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public UserDetails updateUser(Integer id, UserDetails Details) {
        return userRepository.findById(id).map(existing -> {
            
            if (Details.getUsername() != null) existing.setUsername(Details.getUsername());
            if (Details.getUseremailid() != null) existing.setUseremailid(Details.getUseremailid());
            if (Details.getMobilenumber() != 0) existing.setMobilenumber(Details.getMobilenumber());
            if (Details.getGender() != null) existing.setGender(Details.getGender());
            if (Details.getPassword() != null) existing.setPassword(Details.getPassword());
            return userRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
