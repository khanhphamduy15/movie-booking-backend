package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import com.kproject.movie_booking.models.Booking;

public interface BookingService {
    Booking createBooking(Booking booking); // Tạo đơn đặt vé

    Optional<List<Booking>> getBookingsByUser(Long userId); // Lấy danh sách đặt vé của user

    Optional<Booking> getBookingById(Long bookingId); // Lấy chi tiết đơn đặt vé

    void cancelBooking(Long bookingId); // Hủy đơn đặt vé
}
