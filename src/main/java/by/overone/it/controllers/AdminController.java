package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Контроллер, который отвечает за работу функционала администратора
 */
@Controller
@SessionAttributes({"userId", "role"})
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * Метод, который срабатывает по адресу /admin и отображает страницу admin_page
     * на которой отображается список всех пользователей КРОМЕ самого администратора
     * @return страница
     */
    @GetMapping("/admin")
    public String showAdminPanel(Model model) {
        ArrayList<User> userList =
                (ArrayList<User>) userService.getAllUsers()
                        .stream()
                        .filter(i -> !i.getUsername().equals("ADMIN"))
                        .collect(Collectors.toList());

        model.addAttribute("userList", userList);
        return "admin_page";
    }

    /**
     * Метод, который обрабатывает нажатие кнопки Delete/Block/Unblock пользователя
     * @return перенаправление на страницу администратора
     */
    @PostMapping("/admin-action")
    public String doAdminAction() {
        return "redirect:/admin";
    }
}
