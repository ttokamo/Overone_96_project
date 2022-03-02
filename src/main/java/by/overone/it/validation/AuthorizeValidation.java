package by.overone.it.validation;

import by.overone.it.encoder.PasswordEncoder;
import by.overone.it.entity.User;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorizeValidation {

    @Autowired
    private UserService userService;

    public String validateLoginData(String username, String password) {
        String error = "";
        Optional<User> user = userService.getUserByUsername(username);

        if (!user.isPresent()) {
            error = "User not found";
        } else if (!user.get().getPassword().equals(PasswordEncoder.encodePassword(password))) {
            error = "Incorrect password";
        }
        return error;
    }

    public String validateRegistrationData(
            String username,
            String password,
            String repassword
    ) {
        String error = "";

        if (userService.getUserByUsername(username).isPresent()) {
            error = "User already exists";
        } else if (!password.equals(repassword)) {
            error = "Password mismatch";
        }

        return error;
    }
}
