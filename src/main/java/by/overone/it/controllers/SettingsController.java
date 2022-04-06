package by.overone.it.controllers;

import by.overone.it.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс, который отвечает за страницу настроек пользователя
 */
@Controller
@SessionAttributes({"userId", "role"})
public class SettingsController {

    @Autowired
    private UserService userService;

    /**
     * Метод, который отображает страницу настроек
     * @return страница настроек
     */
    @GetMapping("/settings")
    public String showSettingsPage() {
        return "settings_page";
    }

    /**
     * Метод, который принимает изображение с формы и сохраняет по пути /webapp/user-images/{fileName}
     * @param multipartFile файл с формы
     * @param model модель страницы
     * @return страница настроек
     */
    @SneakyThrows
    @PostMapping("/add-photo")
    public String savePhoto(@RequestParam("image") MultipartFile multipartFile, Model model) {
        Path path = Paths.get("src", "main", "webapp", "user-images", multipartFile.getOriginalFilename());
        multipartFile.transferTo(path);
        userService.updatePathToImageById(
                "user-images/" + multipartFile.getOriginalFilename(),
                String.valueOf(model.getAttribute("userId")));
        return "settings_page";
    }
}
