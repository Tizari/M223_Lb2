package ch.zli.m223.ksh20.lb2.model;

import java.time.LocalDate;

public interface Booking {

    Long getId();
    LocalDate getDate();
    boolean isFullDay();
    boolean isAccepted();
    Long getUserId();


}
