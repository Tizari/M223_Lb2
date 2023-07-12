package ch.zli.m223.ksh20.lb2.controller;

import ch.zli.m223.ksh20.lb2.controller.dto.UserDto;
import ch.zli.m223.ksh20.lb2.controller.dto.UserInputDto;
import ch.zli.m223.ksh20.lb2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    void register(@RequestBody UserInputDto user) {
        userService.addUser(
                user.firstName, user.surName,
                user.email, user.password
        );
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserInputDto userInputDto = new UserInputDto();
        model.addAttribute("user", userInputDto);
        return "register";
    }


    @GetMapping()
    List<UserDto> getUserList() {
        return userService.getUserList().stream()
                .map( user -> new UserDto(user))
                .collect(Collectors.toList());
    }



}
