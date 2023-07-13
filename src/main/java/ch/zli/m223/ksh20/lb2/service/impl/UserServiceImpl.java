package ch.zli.m223.ksh20.lb2.service.impl;

import ch.zli.m223.ksh20.lb2.model.AppUser;
import ch.zli.m223.ksh20.lb2.repository.UserRepository;
import ch.zli.m223.ksh20.lb2.service.UserService;
import ch.zli.m223.ksh20.lb2.service.exception.InvalidEmailOrPasswordException;
import ch.zli.m223.ksh20.lb2.service.exception.UserAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<AppUser> getUserList() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public AppUser getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public AppUser addUser(
        String firstName, String surName,
        String email, String password
    ) {
        if (
            email == null || email.isBlank() || 
            password == null || password.isBlank()
        ) {
            throw new InvalidEmailOrPasswordException();
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        return userRepository.insertUser(firstName, surName, email, password);
    }
    
}
