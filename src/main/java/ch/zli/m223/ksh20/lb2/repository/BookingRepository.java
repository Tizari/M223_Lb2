package ch.zli.m223.ksh20.lb2.repository;

import ch.zli.m223.ksh20.lb2.model.Booking;
import ch.zli.m223.ksh20.lb2.model.impl.BookingImpl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<BookingImpl, Long> {
    public default Booking insertBooking(LocalDate date, boolean isFullDay, boolean accepted, Long userId){
        var booking = new BookingImpl(date, isFullDay, accepted, userId);
        return save(booking);
    }

    public default Booking updateBooking(BookingImpl booking){
        return save(booking);
    }
}
