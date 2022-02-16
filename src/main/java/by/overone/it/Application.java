package by.overone.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("by.overone.it.entity")
@EnableJpaRepositories("by.overone.it.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
