package ch.zli.m223.ksh20.lb2.controller.dto;

import java.time.LocalDate;

public class BookingInputDto {
    public LocalDate date;
    public boolean isFullDay, accepted;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isFullDay() {
        return isFullDay;
    }

    public void setFullDay(boolean fullDay) {
        isFullDay = fullDay;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
