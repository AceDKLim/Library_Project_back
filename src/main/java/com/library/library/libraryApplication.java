package com.library.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class libraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(libraryApplication.class, args);
    }
}
