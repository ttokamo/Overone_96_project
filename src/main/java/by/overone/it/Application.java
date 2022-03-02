package by.overone.it;

import by.overone.it.enums.RoleEnums;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("by.overone.it.entity")
@EnableJpaRepositories("by.overone.it.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired UserService userService) {
        return args -> {
            if (!userService.getUserByUsername("ADMIN").isPresent()) {
                userService.save("ADMIN", "ADMIN", RoleEnums.ADMIN.name());
            }
        };
    }

}
