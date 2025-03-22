package com.kproject.movie_booking.security.manager;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kproject.movie_booking.models.User;
import com.kproject.movie_booking.services.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserService userServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            User user = userServiceImpl.getUserByEmail(authentication.getName());
            if (user == null) {
                throw new BadCredentialsException("User not found");
            }
            if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            }
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Authentication failed: " + e.getMessage());
        }
    }

}
