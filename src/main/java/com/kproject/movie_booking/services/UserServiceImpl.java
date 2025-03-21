package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.exceptions.UserNotFoundException;
import com.kproject.movie_booking.models.User;
import com.kproject.movie_booking.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        user.setRole("USER");
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return unwrapUser(user, userId);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return unwrapUser(user, email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, User.class);
    }

    static User unwrapUser(Optional<User> entity, String email) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new UserNotFoundException(email, User.class);
    }
}
