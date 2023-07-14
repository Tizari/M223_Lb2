package ch.zli.m223.ksh20.lb2.controller.dto;

import ch.zli.m223.ksh20.lb2.model.Booking;

import java.time.LocalDate;

public class BookingDto {

    public LocalDate date;
    public boolean isFullDay, accepted;
    public Long id, userId;

    public BookingDto(Booking booking) {

        id = booking.getId();
        date = booking.getDate();
        isFullDay = booking.isFullDay();
        accepted = booking.isAccepted();
        userId = booking.getUserId();
    }
}
