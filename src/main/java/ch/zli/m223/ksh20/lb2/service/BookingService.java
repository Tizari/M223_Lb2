package ch.zli.m223.ksh20.lb2.service;

import ch.zli.m223.ksh20.lb2.model.Booking;
import ch.zli.m223.ksh20.lb2.model.impl.BookingImpl;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    List<Booking> getBookingList();
    BookingImpl getBookingById(Long id);
    Booking addBooking(LocalDate date, boolean isFullDay, boolean accepted, Long userId);
    void updateBooking(Long id, LocalDate date, boolean isFullDay, boolean accepted);
    void deleteBooking(Long id);

}
