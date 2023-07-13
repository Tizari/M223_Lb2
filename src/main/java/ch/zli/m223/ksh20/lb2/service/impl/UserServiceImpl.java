package ch.zli.m223.ksh20.lb2.service.impl;

import ch.zli.m223.ksh20.lb2.model.AppUser;
import ch.zli.m223.ksh20.lb2.model.impl.AppUserImpl;
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
    public AppUserImpl getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public AppUser addUser(String firstName, String lastName,
                           String email, String password) {
        if (password == null || password.equals("") || email == null || email.equals("")) {
            throw new InvalidEmailOrPasswordException();
        }

        if (userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException();
        }

        return userRepository.insertUser(firstName, lastName, email, password, "member");

    }

    @Override
    public AppUser login(String email, String password) {
        return new AppUserImpl("","",email, password, "");
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    public void updateUser(Long id, String firstName, String lastName, String email) {
        AppUserImpl user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            userRepository.save(user);
        }
    }



}
