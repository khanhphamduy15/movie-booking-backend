package com.kproject.movie_booking.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with email '" + email + "' does not exist in our records");
}
}
