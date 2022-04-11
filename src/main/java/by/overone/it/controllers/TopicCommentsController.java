package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.TopicCommentsService;
import by.overone.it.service.UserService;
import by.overone.it.util.FileWorker;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Класс, который отвечает за комментарии пользователей под постами
 */
@Controller
@SessionAttributes("userId")
public class TopicCommentsController {

    @Autowired
    private TopicCommentsService topicCommentsService;
    @Autowired
    private UserService userService;

    /**
     * Принимает данные с формы и сохраняет комментарий в бд
     *
     * @param topicId идентификатор поста
     * @param comment текст комментария
     * @param file    файл
     * @param model   модель страницы
     * @return перенаправление на страницу поста, на которой находился пользователь
     */
    @SneakyThrows
    @PostMapping("/add-comment/{id}")
    public String saveComment(
            @PathVariable("id") String topicId,
            @RequestParam("comment") String comment,
            @RequestParam("commentImage") MultipartFile file,
            Model model
    ) {
        if (!file.isEmpty()) {
            FileWorker.saveFileFromComments(file);
        }
        Optional<User> user = userService.getUserById(String.valueOf(model.getAttribute("userId")));
        topicCommentsService.save(
                topicId,
                user.get().getId(),
                user.get().getUsername(),
                file.getOriginalFilename(),
                comment
        );
        return "redirect:/topic/" + topicId;
    }

    /**
     * Метод, который отвечает за удаление комментария по его идентификатору
     *
     * @param topicId   идентификатор поста
     * @param commentId идентификатор комментария
     * @return перенаправление на страницу поста, на котором находился пользователь
     */
    @PostMapping("/topic/{id}")
    public String deleteComment(
            @PathVariable("id") String topicId,
            @RequestParam("deleteComment") String commentId) {
        topicCommentsService.deleteCommentAndCommentImageById(commentId);
        return "redirect:/topic/" + topicId;
    }
}
