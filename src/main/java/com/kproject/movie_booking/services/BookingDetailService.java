package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import com.kproject.movie_booking.models.BookingDetail;

public interface BookingDetailService {
    BookingDetail addBookingDetail(BookingDetail bookingDetail); // Thêm chi tiết đặt vé

    Optional<List<BookingDetail>> getBookingDetailsByBookingId(Long bookingId); // Lấy danh sách vé theo Booking

    Optional<BookingDetail> getBookingDetailById(Long id); // Lấy thông tin chi tiết vé

    void deleteBookingDetail(Long id); // Xóa chi tiết đặt vé
}
