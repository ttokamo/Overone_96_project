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

@Controller
@SessionAttributes({"userId", "role"})
public class HomeController {

    @Autowired
    private UserService userService;

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
            model.addAttribute("username", user.get().getUsername());
        } else {
            return "redirect:/registration";
        }

        return "home_page";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        ArrayList<User> userList =
                (ArrayList<User>) userService.getAllUsers()
                        .stream()
                        .filter(i -> !i.getUsername().equals("ADMIN"))
                        .collect(Collectors.toList());
        model.addAttribute("usersList", userList);
        return "users";
    }

    private boolean checkIfThisMainUser(String pathId, String currentId) {
        return pathId.equals(currentId);
    }
}
