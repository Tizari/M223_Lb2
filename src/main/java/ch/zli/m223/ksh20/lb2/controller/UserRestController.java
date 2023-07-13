/*
package ch.zli.m223.ksh20.lb2.controller;

import ch.zli.m223.ksh20.lb2.controller.dto.UserDto;
import ch.zli.m223.ksh20.lb2.controller.dto.UserInputDto;
import ch.zli.m223.ksh20.lb2.model.impl.AppUserImpl;
import ch.zli.m223.ksh20.lb2.service.UserService;
import ch.zli.m223.ksh20.lb2.service.exception.InvalidEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        user.setLastName(userInput.getLastName());
        user.setEmail(userInput.getEmail());
        user.setPasswordHash(userInput.getPassword());
        userService.addUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPasswordHash());
        return "redirect:/api/v1/users/all";
    }
}
*/
package ch.zli.m223.ksh20.lb2.controller;

        import ch.zli.m223.ksh20.lb2.controller.dto.UserDto;
        import ch.zli.m223.ksh20.lb2.controller.dto.UserInputDto;
        import ch.zli.m223.ksh20.lb2.controller.dto.UserLoginDto;
        import ch.zli.m223.ksh20.lb2.model.AppUser;
        import ch.zli.m223.ksh20.lb2.model.impl.AppUserImpl;
        import ch.zli.m223.ksh20.lb2.repository.UserRepository;
        import ch.zli.m223.ksh20.lb2.service.UserService;
        import ch.zli.m223.ksh20.lb2.service.exception.InvalidEmailOrPasswordException;
        import jakarta.servlet.http.HttpSession;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

        import java.util.*;
        import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/users")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public String showAll(Model model) {
        List<UserDto> users = userService.getUserList().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "/userList";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "/userLogin";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Optional<AppUser> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                return "redirect:/api/v1/users/all";
            }
        }
        return "redirect:/api/v1/users/login?error";
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
        user.setLastName(userInput.getLastName());
        user.setEmail(userInput.getEmail());
        user.setPasswordHash(userInput.getPassword());
        userService.addUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPasswordHash());
        return "redirect:/api/v1/users/all";
    }

    @GetMapping("/{id}")
    Map<String, String> getUser(@PathVariable Long id) {
        AppUserImpl user = userService.getUserById(id);
        HashMap<String, String> map = new HashMap<>();
        if (Objects.equals(user.getEmail(), "") || user.getEmail().isEmpty()){
            return map;
        }
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("email", user.getEmail());
        return map;
    }

    @PutMapping("/{id}/update")
    void update(@RequestBody UserInputDto userInput, @PathVariable Long id) {
        userService.updateUser(id, userInput.firstName, userInput.lastName, userInput.email);
    }

    @DeleteMapping("/{id}/delete")
    void delete(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        List<AppUser> users = userService.getUserList();
        model.addAttribute("users", users);
    }


}

