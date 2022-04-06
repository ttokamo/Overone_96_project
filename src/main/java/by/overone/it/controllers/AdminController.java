package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.enums.RoleEnums;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
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
     * Отображает страницу добавления пользователя
     * @return страница добавления пользователя
     */
    @GetMapping("/admin/add-user")
    public String showAddUserPage() {
        return "add-user-page";
    }

    /**
     * Метод, который сохраняет пользователя и добавляет к его имени "test".
     * @param username имя пользователя
     * @return страница добавления пользователя (admin)
     */
    @PostMapping("/admin/add-user")
    public String saveNewUser(@RequestParam("username") String username) {
        String refactoredUsername = "test" + username;
        userService.save(refactoredUsername, "123", RoleEnums.USER.name());
        return "add-user-page";
    }

    /**
     * Метод, который обрабатывает нажатие кнопки Delete/Block/Unblock пользователя.
     * Значение кнопки приходит строкой и содержит в себе два слова, где первое слово - действие,
     * а авторое - индетификатор пользователя.
     * @return перенаправление на страницу администратора
     */
    @PostMapping("/admin-action")
    public String getAdminActionButtonValue(@RequestParam("adminActionButton") String buttonValue) {
        String[] splittedButtonValues = buttonValue.split("\\s");
        checkAndTakeAdminAction(splittedButtonValues[0], splittedButtonValues[1]);
        return "redirect:/admin";
    }

    /**
     * Метод, который совершает действие(Удаление/Изменение статуса) над пользователям в бд по id в зависимости от входящего параметра.
     * @param action действие, которое будет происходить (DELETE/BLOCK/UNBLOCK)
     * @param id индентификатор пользователя
     */
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
