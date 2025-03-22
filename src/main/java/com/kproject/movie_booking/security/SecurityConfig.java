package com.kproject.movie_booking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.kproject.movie_booking.security.filter.AuthenticationFilter;
// import com.kproject.movie_booking.security.filter.ExceptionHandlerFilter;
// import com.kproject.movie_booking.security.filter.JWTAuthorizationFilter;
import com.kproject.movie_booking.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
                //         .anyRequest().authenticated())
                // .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                // .addFilter(authenticationFilter)
                // .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class);

        return http.build();
    }

}
