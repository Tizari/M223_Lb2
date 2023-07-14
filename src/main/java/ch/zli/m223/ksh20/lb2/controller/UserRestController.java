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
        import ch.zli.m223.ksh20.lb2.security.JwtResponse;
        import ch.zli.m223.ksh20.lb2.security.JwtUtils;
        import ch.zli.m223.ksh20.lb2.service.UserService;
        import ch.zli.m223.ksh20.lb2.service.exception.InvalidEmailOrPasswordException;
        import jakarta.servlet.http.HttpSession;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
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

    @Autowired
    private JwtUtils jwtUtils;


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
                // Erzeuge das JWT-Token für den Benutzer
                String token = jwtUtils.generateJwtToken(user.getUserName(), user.getRole(), user.getId());

                // Speichere das Token in der Sitzung
                session.setAttribute("jwtToken", token);

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
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        AppUser user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDto userDto = new UserDto(user);
        return ResponseEntity.ok(userDto);
    }


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        AppUser user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/editUser";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") UserInputDto userInput) {
        userService.updateUser(id, userInput.getFirstName(), userInput.getLastName(), userInput.getEmail());
        return "redirect:/api/v1/users/" + id;
    }
//geht noch nicht
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/isAdmin")
    Boolean isAdmin(
            @RequestHeader("Authorization") String header
    ){
        String token = header.split(" ")[0].trim();
        return jwtUtils.getRoleFromJwtToken(token).equals("admin");
    }


}

