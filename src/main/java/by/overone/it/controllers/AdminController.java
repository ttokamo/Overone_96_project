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

    @PostMapping("/admin-action")
    public String doAdminAction(HttpServletRequest request) {
        return "redirect:/admin";
    }
}
