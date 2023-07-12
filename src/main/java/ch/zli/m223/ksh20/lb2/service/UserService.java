package ch.zli.m223.ksh20.lb2.service;

import ch.zli.m223.ksh20.lb2.model.AppUser;


import java.util.List;

public interface UserService {

    List<AppUser> getUserList();

    AppUser getUser(Long id);

    AppUser addUser(
        String firstName, String shureName, 
        String email, String password
    );
    
}
