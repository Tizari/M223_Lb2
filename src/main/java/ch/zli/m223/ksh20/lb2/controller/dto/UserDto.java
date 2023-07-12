package ch.zli.m223.ksh20.lb2.controller.dto;

import ch.zli.m223.ksh20.lb2.model.AppUser;

public class UserDto {
    public String firstName;
    public String surName;
    public String email;

    public UserDto(AppUser user) {
        firstName = user.getFirstName();
        surName =user.getSurName();
        email = user.getUserName();
    }
}
