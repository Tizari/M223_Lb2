package ch.zli.m223.ksh20.lb2.service.impl;


import ch.zli.m223.ksh20.lb2.model.Booking;
import ch.zli.m223.ksh20.lb2.model.impl.BookingImpl;
import ch.zli.m223.ksh20.lb2.repository.BookingRepository;
import ch.zli.m223.ksh20.lb2.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public List<Booking> getBookingList() {
        System.out.println(bookingRepository.findAll().get(0).getDate());
        return new ArrayList<>(bookingRepository.findAll());
    }

    @Override
    public BookingImpl getBookingById(Long id) {
        return bookingRepository.getReferenceById(id);
    }

    @Override
    public Booking addBooking(LocalDate date, boolean isFullDay, boolean accepted, Long userId) {
        // TODO: validation
        return bookingRepository.insertBooking(date, isFullDay, accepted, userId);
    }

    @Override
    public void updateBooking(Long id, LocalDate date, boolean isFullDay, boolean accepted) {
        BookingImpl booking = getBookingById(id);
        booking.setDate(date);
        booking.setFullDay(isFullDay);
        booking.setAccepted(accepted);
        bookingRepository.updateBooking(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.delete(getBookingById(id));
    }
}
