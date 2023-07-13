package ch.zli.m223.ksh20.lb2.controller;

import ch.zli.m223.ksh20.lb2.controller.dto.BookingDto;
import ch.zli.m223.ksh20.lb2.controller.dto.BookingInputDto;
import ch.zli.m223.ksh20.lb2.model.AppUser;
import ch.zli.m223.ksh20.lb2.model.Booking;
import ch.zli.m223.ksh20.lb2.service.BookingService;
import ch.zli.m223.ksh20.lb2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    List<BookingDto> getBookingList(){
        return bookingService.getBookingList().stream()
                .map(booking -> new BookingDto(booking))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
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
    void addBooking(@RequestBody BookingInputDto bookingInput){
        bookingService.addBooking(LocalDate.parse(bookingInput.date), bookingInput.isFullDay, bookingInput.accepted, 1L);
    }

    @DeleteMapping("/{id}")
    void deleteBooking(Model model, @PathVariable Long id){
        bookingService.deleteBooking(id);
        List<Booking> bookings = bookingService.getBookingList();
        model.addAttribute("users", bookings);
    }

}
