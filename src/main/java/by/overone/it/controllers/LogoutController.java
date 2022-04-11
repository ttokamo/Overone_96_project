package by.overone.it.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Класс, который отвечает за выход из аккаунта
 */
@Controller
@SessionAttributes({"userId", "role"})
public class LogoutController {

    /**
     * Метод, который очищает сессию, что бы пользователь мог выйти из аккаунта
     *
     * @param sessionStatus интерфейс для работы с сессией
     * @return перенаправление на страницу авторизации
     */
    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
