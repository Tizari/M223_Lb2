package ch.zli.m223.ksh20.lb2.controller.dto;

import ch.zli.m223.ksh20.lb2.model.AppUser;

public class UserDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public UserDto(AppUser user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName =user.getLastName();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
