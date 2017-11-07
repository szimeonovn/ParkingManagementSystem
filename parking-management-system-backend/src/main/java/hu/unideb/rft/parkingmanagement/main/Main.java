package hu.unideb.rft.parkingmanagement.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("hu.unideb.rft.parkingmanagement")
@EnableJpaRepositories("hu.unideb.rft.parkingmanagement.repository")
@EntityScan("hu.unideb.rft.parkingmanagement.entity")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
