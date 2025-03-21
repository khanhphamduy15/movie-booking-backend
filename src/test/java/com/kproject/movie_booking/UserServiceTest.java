package com.kproject.movie_booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.exceptions.UserNotFoundException;
import com.kproject.movie_booking.models.User;
import com.kproject.movie_booking.repositories.UserRepository;
import com.kproject.movie_booking.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void createUser_Success() {
        User user = new User();
        user.setId(1L);
        user.setFullname("test user");
        user.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.registerUser(user);

        assertNotNull(createdUser);
        assertEquals("test user", createdUser.getFullname());
        assertEquals("test@example.com", createdUser.getEmail());
    }

    @Test
    public void getUserById_Found() {
        User user = new User();
        user.setId(1L);
        user.setFullname("test user");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals("test user", foundUser.getFullname());
    }

    @Test(expected = EntityNotFoundException.class)
    public void getUserById_NotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        userService.getUserById(2L);
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserByEmail_NotFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        userService.getUserByEmail("test@example.com");
    }

}
