package by.overone.it.controllers;

import by.overone.it.enums.RoleEnums;
import by.overone.it.service.UserService;
import by.overone.it.validation.AuthorizeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Класс, который отвечает за регистрацию пользователя
 */
@Controller
@SessionAttributes({"userId", "role"})
public class RegistrationController {

    private UserService userService;
    private AuthorizeValidation authorizeValidation;

    @Autowired
    public RegistrationController(UserService userService, AuthorizeValidation authorizeValidation) {
        this.userService = userService;
        this.authorizeValidation = authorizeValidation;
    }

    /**
     * Метод, который отображает страницу регистрации
     * @return страница регистрации
     */
    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "register_page";
    }

    /**
     * Метод, который принимает введенные данные с формы регистрации и отправляет их на проверку.
     * Если проверка не прошла - возращает страницу регистрации и отображает сообщение об ошибке.
     * Если проверка прошла успешно - перенаправляет на страницу авторизации.
     * @param username имя пользователя с формы
     * @param password пароль с формы
     * @param repassword повторение пароля с формы
     * @param model модель страницы
     * @return либо страница регистрации, либо перенаправление на страницу авторизации
     */
    @PostMapping("/registration")
    public String checkInputData(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("repassword") String repassword,
            Model model
    ) {
        String error = authorizeValidation.validateRegistrationData(username, password, repassword);

        if (!error.isEmpty()) {
            model.addAttribute("error", error);
            return "register_page";
        } else {
            userService.save(username, password, RoleEnums.USER.name());
            return "redirect:/";
        }
    }
}
