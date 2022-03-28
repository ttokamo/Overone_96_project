package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.TopicCommentsService;
import by.overone.it.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@SessionAttributes("userId")
public class TopicCommentsController {

    @Autowired
    private TopicCommentsService topicCommentsService;
    @Autowired
    private UserService userService;

    @SneakyThrows
    @PostMapping("/add-comment/{id}")
    public String saveComment(
            @PathVariable("id") String topicId,
            @RequestParam("comment") String comment,
            @RequestParam("commentImage") MultipartFile file,
            Model model)
    {
        Path path = Paths.get(
                "src",
                "main",
                "webapp",
                "topic-images",
                "topic-comments-images",
                file.getOriginalFilename()
        );
        file.transferTo(path);
        Optional<User> user = userService.getUserById(String.valueOf(model.getAttribute("userId")));
        topicCommentsService.save(
                topicId,
                user.get().getId(),
                user.get().getUsername(),
                "topic-images/topic-comments-images/" + file.getOriginalFilename(),
                comment
        );
        return "redirect:/topic/" + topicId;
    }

    @PostMapping("/topic/{id}")
    public String deleteComment(
            @PathVariable("id") String topicId,
            @RequestParam("deleteComment") String commentId)
    {
        topicCommentsService.deleteCommentAndCommentImageById(commentId);
        return "redirect:/topic/" + topicId;
    }
}