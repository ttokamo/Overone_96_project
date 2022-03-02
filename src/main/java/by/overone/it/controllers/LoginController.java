package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.UserService;
import by.overone.it.validation.AuthorizeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes({"userId", "role"})
public class LoginController {

    private UserService userService;
    private AuthorizeValidation authorizeValidation;

    @Autowired
    public LoginController(UserService userService, AuthorizeValidation authorizeValidation) {
        this.userService = userService;
        this.authorizeValidation = authorizeValidation;
    }

    @GetMapping({"/", "/login"})
    public String showLoginPage() {
        return "login_page";
    }

    @PostMapping("/login")
    public String checkInputData(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        String error = authorizeValidation.validateLoginData(username, password);

        if (!error.isEmpty()) {
            model.addAttribute("error", error);
            return "login_page";
        } else {
            Optional<User> user = userService.getUserByUsername(username);
            model.addAttribute("userId", user.get().getId());
            model.addAttribute("role", user.get().getRole());
            return "redirect:/user/" + user.get().getId();
        }
    }

}
