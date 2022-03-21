package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Контроллер, который отвечает за работу домашней страницы пользователя
 */
@Controller
@SessionAttributes({"userId", "role"})
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * Метод, который отображает страницу профиля пользователя по адресу /user/{id}
     * где {id} - идентификатор пользователя. Содержит в себе логику проверки пользователя на существование,
     * а так же перенаправляет всех неавторизированных пользователей на страницу регистрации
     * @param id идентификатор из адресной строки
     */
    @GetMapping("/user/{id}")
    public String showHomePage(@PathVariable("id") String id, Model model) {
        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            String flag = "thisIsMainUser";
            if (checkIfThisMainUser(id, model.getAttribute("userId").toString())) {
                model.addAttribute(flag, true);
            } else {
                model.addAttribute(flag, false);
            }
            model.addAttribute("image", user.get().getPathToImage());
            model.addAttribute("username", user.get().getUsername());
        } else {
            return "redirect:/registration";
        }

        return "home_page";
    }

    /**
     * Метод, который отображает страницу со списком всех пользователей КРОМЕ администратора
     * @return страница со списком пользователей
     */
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        ArrayList<User> userList =
                (ArrayList<User>) userService.getAllUsers()
                        .stream()
                        .filter(i -> !i.getUsername().equals("ADMIN"))
                        .filter(i -> !i.getUsername().startsWith("test"))
                        .collect(Collectors.toList());
        model.addAttribute("usersList", userList);
        return "users";
    }

    /**
     * Метод, который сравнивает id из сессии и id из адресной строки.
     * Нужен для того что бы различать свою и чужие страницы
     * @param pathId id из адресной строки
     * @param currentId id главного пользователя
     * @return true или false
     */
    private boolean checkIfThisMainUser(String pathId, String currentId) {
        return pathId.equals(currentId);
    }
}
