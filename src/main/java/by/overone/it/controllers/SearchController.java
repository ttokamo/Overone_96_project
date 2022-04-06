package by.overone.it.controllers;

import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Класс, отвечающий за поиск
 */
@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    /**
     * Метод, который ищет имя пользователя по введенному тексту
     * @param userSearch введенный текст
     * @param model модель страницы
     * @return возвращает страницу с результатами поиска
     */
    @PostMapping("/users")
    public String searchUsers(@RequestParam("userSearch") String userSearch, Model model) {
        model.addAttribute("usersList", userService.searchUserUsernameByInputText(userSearch));
        return "users";
    }
}
