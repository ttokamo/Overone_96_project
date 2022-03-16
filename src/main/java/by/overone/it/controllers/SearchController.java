package by.overone.it.controllers;

import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public String searchUsers(@RequestParam("userSearch") String userSearch, Model model) {
        model.addAttribute("usersList", userService.searchUserUsernameByInputText(userSearch));
        return "users";
    }
}
