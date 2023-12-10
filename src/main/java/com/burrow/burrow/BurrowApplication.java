package com.burrow.burrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BurrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurrowApplication.class, args);
    }

}
