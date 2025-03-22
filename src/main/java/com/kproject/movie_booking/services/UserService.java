package com.kproject.movie_booking.services;

import java.util.List;

import com.kproject.movie_booking.models.User;

public interface UserService {
    User registerUser(User user); 

    User getUserById(Long userId); 

    User getUserByEmail(String email);

    List<User> getAllUsers();
    
    User registerAdmin(User admin); 
}
