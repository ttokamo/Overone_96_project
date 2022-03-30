package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.TopicCommentsService;
import by.overone.it.service.TopicService;
import by.overone.it.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@SessionAttributes({"userId", "role"})
public class TopicController {

    private final TopicService topicService;
    private final UserService userService;
    private final TopicCommentsService topicCommentsService;

    @Autowired
    public TopicController(TopicService topicService, UserService userService, TopicCommentsService topicCommentsService) {
        this.topicService = topicService;
        this.userService = userService;
        this.topicCommentsService = topicCommentsService;
    }

    @GetMapping("/topics")
    public String showPageWithListOfTopics(Model model) {
        model.addAttribute("topicsList", topicService.getAllTopics());
        model.addAttribute("thisIsCreationTopicStage", false);
        return "topics_page";
    }

    @GetMapping("/create-topic")
    public String showPageForCreatingTopic(Model model) {
        model.addAttribute("thisIsCreationTopicStage", true);
        return "topics_page";
    }

    @SneakyThrows
    @PostMapping("/create-topic")
    public String createTopic(
            @RequestParam("topicName") String topicName,
            @RequestParam("topicMessage") String topicMessage,
            @RequestParam("topicImage") MultipartFile file,
            Model model
    ) {
        Path path = Paths.get("src", "main", "webapp", "topic-images", file.getOriginalFilename());
        file.transferTo(path);
        Optional<User> author = userService.getUserById(String.valueOf(model.getAttribute("userId")));
        topicService.save(
                author.get().getId(),
                author.get().getUsername(),
                topicName,
                file.getOriginalFilename(),
                topicMessage);
        return "redirect:/topics";
    }

    @GetMapping("/topic/{id}")
    public String showSingleTopicPage(@PathVariable("id") String topicId, Model model) {
        model.addAttribute("topic", topicService.getTopicById(topicId));
        model.addAttribute("comments", topicCommentsService.getCommentsListByTopicId(topicId));
        return "topic_page";
    }

    @PostMapping("/delete-topic")
    public String deleteTopic(@RequestParam("deleteTopicButton") String topicId) {
        topicService.deleteTopicAndCommentsByTopicId(topicId);
        return "redirect:/topics";
    }

}
