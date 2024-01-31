package com.example.demo.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.FEBRUARY;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository repository) {
        return args -> {
            Client mahdi = new Client(
                    "Mahdi",
                    "mahdikhandaker@hotmail.com",
                    LocalDate.of(1998, FEBRUARY, 22)
            );

            Client mahatab = new Client(
                    "Mahatab",
                    "mahatab2011@hotmail.com",
                    LocalDate.of(2000, FEBRUARY, 19)
            );
            repository.saveAll(
                    List.of(mahdi, mahatab)
            );
        };
    }
}
