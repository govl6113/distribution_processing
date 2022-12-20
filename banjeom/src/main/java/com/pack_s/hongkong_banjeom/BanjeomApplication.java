package com.pack_s.hongkong_banjeom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class BanjeomApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanjeomApplication.class, args);
    }

}
