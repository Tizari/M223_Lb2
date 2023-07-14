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
    @ResponseBody
    public Map<String, String> getBooking(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        AppUser user = userService.getUserById(booking.getUserId());
        HashMap<String, String> map = new HashMap<>();
        map.put("date", booking.getDate().toString());
        map.put("isFullDay", String.valueOf(booking.isFullDay()));
        map.put("accepted", String.valueOf(booking.isAccepted()));
        map.put("user", user.getFirstName() + "" + user.getLastName());
        return map;
    }




    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "/editBooking";
    }

    @PostMapping("/edit/{id}")
    public String updateBooking(@PathVariable Long id, @ModelAttribute("booking") BookingInputDto bookingInput) {
        bookingService.updateBooking(id, bookingInput.getDate(), bookingInput.isFullDay(), bookingInput.isAccepted());
        return "redirect:/api/v1/bookings/show/" + id;
    }




    @GetMapping("/add")
    public String showBookingForm(Model model, Principal principal) {
        // Überprüfen, ob ein Benutzer angemeldet ist
        String username = principal != null ? principal.getName() : null;
        if (username == null) {
            // Kein Benutzer angemeldet, Fehler anzeigen oder Weiterleitung zu einer Fehlerseite
            return "error";
        }

        // Buchungsformular anzeigen
        BookingImpl booking = new BookingImpl();
        booking.setUserId(userService.getUserByEmail(username).get().getId().longValue());
        model.addAttribute("booking", booking);
        return "/createBooking";
    }

    @PostMapping("/add")
    public String addBooking(@ModelAttribute("booking") Booking booking) {
        // Überprüfen, ob ein Benutzer angemeldet ist
        String username = userService.getUserById(booking.getUserId()).getUserName();
        if (username == null || username.isEmpty()) {
            // Kein Benutzer angegeben, Fehler anzeigen oder Weiterleitung zu einer Fehlerseite
            return "error";
        }

        // Hier kannst du den entsprechenden Code für die POST-Anforderung implementieren
        // Verarbeite die übergebenen Buchungsinformationen und füge sie der Datenbank hinzu

        // Beispiel: Speichern der Buchung in der Datenbank
        bookingRepository.insertBooking(booking.getDate(), booking.isFullDay(), booking.isAccepted(), booking.getUserId());



        return "redirect:/api/v1/bookings/all";
    }






    @DeleteMapping("/delete/{id}")
    void deleteBooking(Model model, @PathVariable Long id){
        bookingService.deleteBooking(id);
        List<Booking> bookings = bookingService.getBookingList();
        model.addAttribute("users", bookings);
    }

}
