package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.enums.StatusEnums;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"userId", "role"})
public class AdminController {

    @Autowired
    private UserService userService;

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

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/block/{id}")
    public String blockUser(@PathVariable("id") String id) {
        userService.updateUserStatusById(StatusEnums.BLOCKED.name(), id);
        return "redirect:/admin";
    }

    @GetMapping("/unblock/{id}")
    public String unblockUser(@PathVariable("id") String id) {
        userService.updateUserStatusById(StatusEnums.ACTIVE.name(), id);
        return "redirect:/admin";
    }
}
