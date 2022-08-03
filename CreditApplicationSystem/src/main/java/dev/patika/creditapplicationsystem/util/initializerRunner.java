package dev.patika.creditapplicationsystem.util;

import dev.patika.creditapplicationsystem.model.User;
import dev.patika.creditapplicationsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initializerRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(initializerRunner.class);

    private final UserRepository userRepository;

    public initializerRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findAll().isEmpty()) {
            userRepository.save(User.builder().fullName("Meryem").identityNumber(99854771212L).phoneNumber(5896451214L).salary(6300).build());
            userRepository.save(User.builder().fullName("Mark").identityNumber(68789121514L).phoneNumber(6548487874L).salary(5310).build());
            userRepository.save(User.builder().fullName("John").identityNumber(32528978412L).phoneNumber(9154544847L).salary(3040).build());
            userRepository.save(User.builder().fullName("Muhammet").identityNumber(87965121126L).phoneNumber(7587545412L).salary(7500).build());
            userRepository.save(User.builder().fullName("Arnaf").identityNumber(98953321510L).phoneNumber(3659597944L).salary(2300).build());
            userRepository.save(User.builder().fullName("shikamaru").identityNumber(87421202016L).phoneNumber(9455665656L).salary(6500).build());
            userRepository.save(User.builder().fullName("shancai").identityNumber(36548745418L).phoneNumber(3659445454L).salary(4900).build());

            userRepository.findAll().forEach(user -> logger.info("{}", user));
        }

    }
}
