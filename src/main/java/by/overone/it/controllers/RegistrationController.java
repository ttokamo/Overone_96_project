package by.overone.it.controllers;

import by.overone.it.enums.RoleEnums;
import by.overone.it.service.UserService;
import by.overone.it.validation.AuthorizeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userId", "role"})
public class RegistrationController {

    private UserService userService;
    private AuthorizeValidation authorizeValidation;

    @Autowired
    public RegistrationController(UserService userService, AuthorizeValidation authorizeValidation) {
        this.userService = userService;
        this.authorizeValidation = authorizeValidation;
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "register_page";
    }

    @PostMapping("/registration")
    public String checkInputData(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("repassword") String repassword,
            Model model
    ) {
        String error = authorizeValidation.validateRegistrationData(username, password, repassword);

        if (!error.isEmpty()) {
            model.addAttribute("error", error);
            return "register_page";
        } else {
            userService.save(username, password, RoleEnums.USER.name());
            return "redirect:/";
        }
    }
}
