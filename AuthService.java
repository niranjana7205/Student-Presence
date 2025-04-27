package com.geofence.attendance.service;

import com.geofence.attendance.model.Student;
import com.geofence.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private JwtService jwtService;

    public String authenticate(String email, String password) {
        Student student = studentRepo.findByEmail(email);
        if (student != null && student.getPassword().equals(password)) {
            // Password matches ✅
            return jwtService.generateToken(email);
        }
        return null; // Authentication failed
    }
}

