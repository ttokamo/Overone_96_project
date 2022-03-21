package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.enums.AdminActionEnums;
import by.overone.it.enums.RoleEnums;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static by.overone.it.enums.AdminActionEnums.*;


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

    @GetMapping("/admin/add-user")
    public String showAddUserPage() {
        return "add-user-page";
    }

    @PostMapping("/admin/add-user")
    public String saveNewUser(@RequestParam("username") String username) {
        String refactoredUsername = "test" + username;
        userService.save(refactoredUsername, "123", RoleEnums.USER.name());
        return "add-user-page";
    }

    /**
     * Метод, который обрабатывает нажатие кнопки Delete/Block/Unblock пользователя
     * @return перенаправление на страницу администратора
     */
    @PostMapping("/admin-action")
    public String getAdminActionButtonValue(@RequestParam("adminActionButton") String buttonValue) {
        String[] splittedButtonValues = buttonValue.split("\\s");
        checkAndTakeAdminAction(splittedButtonValues[0], splittedButtonValues[1]);
        return "redirect:/admin";
    }

    private void checkAndTakeAdminAction(String action, String id) {
        switch (action) {
            case "DELETE":
                userService.deleteUserById(id);
                break;
            case "BLOCK":
                userService.updateUserStatusById("BLOCK", id);
                break;
            case "UNBLOCK":
                userService.updateUserStatusById("ACTIVE", id);
                break;
        }
    }
}
