package ch.zli.m223.ksh20.lb2.controller;

import ch.zli.m223.ksh20.lb2.controller.dto.UserDto;
import ch.zli.m223.ksh20.lb2.controller.dto.UserInputDto;
import ch.zli.m223.ksh20.lb2.model.AppUser;
import ch.zli.m223.ksh20.lb2.model.impl.AppUserImpl;
import ch.zli.m223.ksh20.lb2.service.UserService;
import ch.zli.m223.ksh20.lb2.service.exception.InvalidEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/users")
public class UserRestController {


    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public String showAll(Model model) {
        List<UserDto> users = userService.getUserList().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "/userList";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserInputDto());
        return "/addUser";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserInputDto userInput, Model model) {
        if (userInput.email== null || userInput.email.isEmpty() ||
                userInput.password == null || userInput.password.isEmpty()) {
            throw new InvalidEmailOrPasswordException();
        }
        AppUserImpl user = new AppUserImpl();
        user.setFirstName(userInput.getFirstName());
        user.setSurName(userInput.getSurName());
        user.setEmail(userInput.getEmail());
        user.setPasswordHash(userInput.getPassword());
        userService.addUser(user.getFirstName(), user.getSurName(), user.getEmail(), user.getPasswordHash());
        return "redirect:/api/v1/users/all";
    }
}


