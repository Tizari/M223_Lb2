package ch.zli.m223.ksh20.lb2.controller;

import ch.zli.m223.ksh20.lb2.controller.dto.BookingDto;
import ch.zli.m223.ksh20.lb2.controller.dto.BookingInputDto;
import ch.zli.m223.ksh20.lb2.controller.dto.UserDto;
import ch.zli.m223.ksh20.lb2.model.AppUser;
import ch.zli.m223.ksh20.lb2.model.Booking;
import ch.zli.m223.ksh20.lb2.model.impl.AppUserImpl;
import ch.zli.m223.ksh20.lb2.model.impl.BookingImpl;
import ch.zli.m223.ksh20.lb2.repository.BookingRepository;
import ch.zli.m223.ksh20.lb2.service.BookingService;
import ch.zli.m223.ksh20.lb2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.constant.Constable;
import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/bookings")
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/all")
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.getBookingList();
        model.addAttribute("bookings", bookings);
        List<UserDto> users = userService.getUserList().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "/bookingList";
    }

    @GetMapping("/show/{id}")
    Map<String, String> getBooking(@PathVariable Long id){
        Booking booking = bookingService.getBookingById(id);
        AppUser user = userService.getUserById(booking.getUserId());
        HashMap<String, String> map = new HashMap<>();
        map.put("date", booking.getDate().toString());
        map.put("isFullDay", String.valueOf(booking.isFullDay()));
        map.put("accepted", String.valueOf(booking.accepted()));
        map.put("user", user.getFirstName() + "" + user.getLastName());
        return map;
    }

    @PutMapping("/{id}/update")
    void update(@RequestBody BookingInputDto bookingInput, @PathVariable Long id) {
        bookingService.updateBooking(id, LocalDate.parse(bookingInput.date), bookingInput.isFullDay, bookingInput.accepted);
    }

    @PostMapping("/add")
    String addBooking(@RequestBody BookingInputDto bookingInput, Principal principal, RedirectAttributes redirectAttributes) {
        if (principal == null) {
            redirectAttributes.addFlashAttribute("error", "Benutzer ist nicht angemeldet");
            return "redirect:/index";
        }

        String username = principal.getName();
        Optional<AppUser> user = userService.getUserByEmail(username);
        if (user.isPresent()) {
            try {
                Long userId = user.get().getId();
                BookingImpl booking = (BookingImpl) bookingService.addBooking(LocalDate.parse(bookingInput.date), bookingInput.isFullDay, bookingInput.accepted, userId);
                bookingRepository.insertBooking(booking.getDate(), booking.isFullDay(), booking.accepted(),booking.getUserId());
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Fehler beim Hinzufügen der Buchung");
            }
        }

        return "redirect:/api/v1/bookings/all";
    }





    @DeleteMapping("/delete/{id}")
    void deleteBooking(Model model, @PathVariable Long id){
        bookingService.deleteBooking(id);
        List<Booking> bookings = bookingService.getBookingList();
        model.addAttribute("users", bookings);
    }

}
