package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import com.kproject.movie_booking.models.User;

public interface UserService {
    User registerUser(User user); // Đăng ký tài khoản

    Optional<User> getUserById(Long userId); // Tìm user theo ID

    Optional<User> getUserByEmail(String email); // Tìm user theo email

    List<User> getAllUsers(); // Lấy danh sách người dùng
}
