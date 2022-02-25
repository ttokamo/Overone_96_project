package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("userId")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public String showHomePage(@PathVariable("id") String id, Model model) {
        String username = userService.getUserById(id).getUsername();
        model.addAttribute("username", username);
        return "home_page";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("usersList", userList);
        return "users";
    }
}
