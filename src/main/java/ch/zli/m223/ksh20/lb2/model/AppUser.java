package ch.zli.m223.ksh20.lb2.model;

public interface AppUser {
    Long getId();
    String getUserName(); //his email
    String getFirstName();
    String getLastName();
    String getPassword();

        String getRole();

        void setFirstname(String firstName);

        void setLastname(String lastName);

        void setEmail(String email);
    }

