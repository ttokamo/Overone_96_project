package by.overone.it.validation;

import by.overone.it.encoder.PasswordEncoder;
import by.overone.it.entity.User;
import by.overone.it.enums.StatusEnums;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Класс валидации данных
 */
@Component
public class AuthorizeValidation {

    @Autowired
    private UserService userService;

    /**
     * Проверяет данные с формы авторизации. В хорошем случае возвращает пустую строку.
     * В плохом случае возвращает строку с ошибкой
     *
     * @param username имя пользователя
     * @param password пароль
     * @return пустая строка либо строка с ошибкой
     */
    public String validateLoginData(String username, String password) {
        String error = "";
        Optional<User> user = userService.getUserByUsername(username);

        if (!user.isPresent()) {
            error = "User not found";
        } else if (!user.get().getPassword().equals(PasswordEncoder.encodePassword(password))) {
            error = "Incorrect password";
        } else if (user.get().getStatus().equals(StatusEnums.BLOCKED.name())) {
            error = "Account is blocked";
        }
        return error;
    }

    /**
     * Проверяет данные с формы регистрации. В хорошем случае возвращает пустую строку.
     * В плохом случае возвращает строку с ошибкой.
     *
     * @param username   имя пользователя
     * @param password   пароль
     * @param repassword поторение пароля
     * @return пустая строка либо строка с ошибкой
     */
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
