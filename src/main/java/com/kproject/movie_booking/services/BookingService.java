package com.kproject.movie_booking.services;

import java.util.List;

import com.kproject.movie_booking.models.Booking;

public interface BookingService {
    Booking createBooking(Booking booking, Long userId, Long showtimeId); // Tạo đơn đặt vé

    List<Booking> getBookingsByUser(Long userId); // Lấy danh sách đặt vé của user

    Booking getBookingById(Long bookingId); // Lấy chi tiết đơn đặt vé

    void cancelBooking(Long bookingId); // Hủy đơn đặt vé

    List<Booking> getAllBookings();

    void deleteBookingById(Long bookingId);
}
