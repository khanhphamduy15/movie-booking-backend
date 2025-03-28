package com.kproject.movie_booking.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kproject.movie_booking.exceptions.EntityNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String header = request.getHeader("Authorization");
                System.out.println("Authorization Header: " + header); // Log để kiểm tra
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException e) {
            sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Email doesn't exist");
        } catch (JWTVerificationException e) {
            sendErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "JWT NOT VALID");
        } catch (RuntimeException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "BAD REQUEST");
        }
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
        response.getWriter().flush();
    }
}
