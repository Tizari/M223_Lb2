package ch.zli.m223.ksh20.lb2.model.impl;

import ch.zli.m223.ksh20.lb2.model.AppUser;
import ch.zli.m223.ksh20.lb2.model.Booking;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity(name = "Bookings")
public class BookingImpl implements Booking {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "isFullDay")
    private boolean isFullDay;

    @Column(name = "accepted")
    private boolean accepted;

    @Column
    private Long userId;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUserImpl user;
*/


    public BookingImpl(LocalDate date, boolean isFullDay, boolean accepted, Long userId) {
        this.date = date;
        this.isFullDay = isFullDay;
        this.accepted = accepted;
        this.userId = userId;
    }

    public BookingImpl() {

    }

    public Long getId() {
        return id;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean isFullDay() {
        return isFullDay;
    }

    @Override
    public boolean isAccepted() {
        return accepted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setFullDay(boolean fullDay) {
        isFullDay = fullDay;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
