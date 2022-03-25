package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.TopicCommentsService;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("userId")
public class TopicCommentsController {

    @Autowired
    private TopicCommentsService topicCommentsService;
    @Autowired
    private UserService userService;

    @PostMapping("/add-comment/{id}")
    public String saveComment(
            @PathVariable("id") String topicId,
            @RequestParam("comment") String comment,
            Model model)
    {
        Optional<User> user = userService.getUserById(String.valueOf(model.getAttribute("userId")));
        topicCommentsService.save(topicId, user.get().getId(), user.get().getUsername(), comment);
        return "redirect:/topic/" + topicId;
    }

    @PostMapping("/topic/{id}")
    public String deleteComment(
            @PathVariable("id") String topicId,
            @RequestParam("deleteComment") String commentId)
    {
        topicCommentsService.deleteCommentById(commentId);
        return "redirect:/topic/" + topicId;
    }
}
