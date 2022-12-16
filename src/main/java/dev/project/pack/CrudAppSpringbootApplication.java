package dev.project.pack;

import dev.project.pack.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("dev.project.pack.repository")
public class CrudAppSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudAppSpringbootApplication.class, args);
    }
}
