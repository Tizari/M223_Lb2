package ch.zli.m223.ksh20.lb2.model.impl;


import ch.zli.m223.ksh20.lb2.model.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="AppUser")
public class AppUserImpl implements AppUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String passwordHash;

    private String firstName;

    private String surName;

    @Column(
        nullable = false, 
        unique = true)
    private String email;

        public AppUserImpl(
        String firstName, String surName,
        String email, String password
    ) {
        this.firstName  = firstName;
        this.surName = surName;
        this.email  = email;
        this.passwordHash  = password;
        
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
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getSurName() {
        return surName;
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

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
