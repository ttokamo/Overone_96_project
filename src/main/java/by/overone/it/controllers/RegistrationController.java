package by.overone.it.controllers;

import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "register_page";
    }

    @PostMapping("/registration")
    public String checkInputData(
            @RequestParam("username") String name,
            @RequestParam("password") String password,
            @RequestParam("repassword") String repassword
    ) {
        userService.save(name, password);
        return "register_page";
    }
}
