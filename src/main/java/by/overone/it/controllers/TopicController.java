package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.TopicService;
import by.overone.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes({"userId", "role"})
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

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

    @PostMapping("/create-topic")
    public String createTopic(
            @RequestParam("topicName") String topicName,
            @RequestParam("topicMessage") String topicMessage,
            Model model
    ) {
        Optional<User> author = userService.getUserById(String.valueOf(model.getAttribute("userId")));
        topicService.save(
                author.get().getId(),
                author.get().getUsername(),
                topicName,
                topicMessage);
        return "redirect:/topics";
    }

    @GetMapping("/topic/{id}")
    public String showSingleTopicPage(@PathVariable("id") String id, Model model) {
        model.addAttribute("topic", topicService.getTopicById(id));
        return "topic_page";
    }
}
