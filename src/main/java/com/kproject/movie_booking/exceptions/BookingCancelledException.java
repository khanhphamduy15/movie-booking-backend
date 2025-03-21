package com.kproject.movie_booking.exceptions;

public class BookingCancelledException extends RuntimeException {
    public BookingCancelledException(Long id, Class<?> entity) {
        super("The booking is already cancelled");
}

}
