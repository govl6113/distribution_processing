package com.pack_s.mijung_noodles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NoodlesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoodlesApplication.class, args);
    }

}
