package ch.zli.m223.ksh20.lb2.init;

import ch.zli.m223.ksh20.lb2.repository.BookingRepository;
import ch.zli.m223.ksh20.lb2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ServerInitialisation implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.insertUser("Max", "Werner", "mw@test.com", "maxtrax", "admin");
        userRepository.insertUser("Mini", "Max", "mm@test.com", "minimax", "member");
        userRepository.insertUser("Lady", "gGaga", "lady@gaga.com", "isgaga", "guest");
        bookingRepository.insertBooking(LocalDate.of(2023, 6, 6), true, false, 1L);
        bookingRepository.insertBooking(LocalDate.of(2023, 4, 5), false, false, 2L);
        bookingRepository.insertBooking(LocalDate.of(2023, 12, 27), false, true, 1L);
    }
    
}
