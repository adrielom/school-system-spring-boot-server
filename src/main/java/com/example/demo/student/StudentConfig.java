package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student evy = new Student(
                    "Evy",
                    LocalDate.of(2019, Month.MAY, 4));
            Student adriel = new Student(
                    "Adriel",
                    LocalDate.of(1994, Month.FEBRUARY, 1));
            repository.saveAll(List.of(evy, adriel));
        };
    }
}
