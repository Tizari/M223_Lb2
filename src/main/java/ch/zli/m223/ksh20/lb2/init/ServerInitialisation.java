package ch.zli.m223.ksh20.lb2.init;

import ch.zli.m223.ksh20.lb2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerInitialisation implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.insertUser("Max", "Werner", "mw@test.com", "maxtrax");
        userRepository.insertUser("Mini", "Max", "mm@test.com", "minimax");
        userRepository.insertUser("Lady", "gGaga", "lady@gaga.com", "isgaga");
    }
    
}
