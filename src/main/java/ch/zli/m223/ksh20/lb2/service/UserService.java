package ch.zli.m223.ksh20.lb2.service;

import ch.zli.m223.ksh20.lb2.model.AppUser;
import ch.zli.m223.ksh20.lb2.model.impl.AppUserImpl;
import org.springframework.boot.autoconfigure.security.SecurityProperties;


import java.util.List;
import java.util.Optional;

public interface UserService {
    List<AppUser> getUserList();

    AppUserImpl getUserById(Long id);

    AppUser addUser(String firstName, String lastName,
                    String email, String password);

    AppUser login(String email, String password);
    void deleteUser(Long id);

    void updateUser(Long id, String firstName, String lastName,
                    String email);

    Optional<AppUser> getUserByEmail(String username);



}

