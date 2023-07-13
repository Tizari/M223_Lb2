package ch.zli.m223.ksh20.lb2.controller.dto;

import ch.zli.m223.ksh20.lb2.model.AppUser;

public class UserDto {
    public Long id;
    public String firstName;
    public String surName;
    public String email;
    public String password;

    public UserDto(AppUser user) {
        id = user.getId();
        firstName = user.getFirstName();
        surName =user.getSurName();
        email = user.getUserName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
