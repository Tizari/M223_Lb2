package ch.zli.m223.ksh20.lb2.model.impl;
import ch.zli.m223.ksh20.lb2.model.AppUser;

import jakarta.persistence.*;

import java.util.List;



@Entity(name="AppUser")
public class AppUserImpl implements AppUser {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column()
    private String role = "guest";

    public AppUserImpl(
            String firstName, String lastName,
            String email, String password, String role
    ) {
        this.firstName  = firstName;
        this.lastName = lastName;
        this.email  = email;
        this.passwordHash  = password;

        this.role = role;
        passwordHash = password; // DODO: hash it
    }


    /** For JPA use only */
    public AppUserImpl() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUserName() {
        return email;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setFirstname(String firstName) {

    }

    @Override
    public void setLastname(String lastName) {

    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
