package ch.zli.m223.ksh20.lb2.repository;


import ch.zli.m223.ksh20.lb2.model.AppUser;
import ch.zli.m223.ksh20.lb2.model.impl.AppUserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUserImpl, Long> {

    public default AppUser insertUser(String firstName, String surName, String email, String password) {
        return save(new AppUserImpl(firstName, surName, email, password));
    }

    public Optional<AppUser> findByEmail(String email);
}
