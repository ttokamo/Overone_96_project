package by.overone.it.controllers;

import by.overone.it.entity.User;
import by.overone.it.service.TopicCommentsService;
import by.overone.it.service.TopicService;
import by.overone.it.service.UserService;
import by.overone.it.util.FileWorker;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    /**
     * Показывает список существующих обсуждений
     *
     * @param model модель
     * @return страница с обсуждениями
     */
    @GetMapping("/topics")
    public String showPageWithListOfTopics(Model model) {
        model.addAttribute("topicsList", topicService.getAllTopics());
        model.addAttribute("thisIsCreationTopicStage", false);
        return "topics_page";
    }

    /**
     * Показывает страницу с формой для создания обсуждения
     *
     * @param model модель
     * @return страница создания обсуждения
     */
    @GetMapping("/create-topic")
    public String showPageForCreatingTopic(Model model) {
        model.addAttribute("thisIsCreationTopicStage", true);
        return "topics_page";
    }

    /**
     * Принимает данные с формы создания обсуждения и отправляет данные на сохранение в сервисный класс
     *
     * @param topicName    имя топика
     * @param topicMessage текст топика
     * @param file         изображение или файл
     * @param model        модель
     * @return перенаправление на страницу со списком обсуждений
     */
    @SneakyThrows
    @PostMapping("/create-topic")
    public String createTopic(
            @RequestParam("topicName") String topicName,
            @RequestParam("topicMessage") String topicMessage,
            @RequestParam("topicImage") MultipartFile file,
            Model model
    ) {
        if (!file.isEmpty()) {
            FileWorker.saveFileFromTopic(file);
        }

        Optional<User> author = userService.getUserById(String.valueOf(model.getAttribute("userId")));
        topicService.save(
                author.get().getId(),
                author.get().getUsername(),
                topicName,
                file.getOriginalFilename(),
                topicMessage);
        return "redirect:/topics";
    }

    /**
     * Показывает личную страницу обсуждения
     *
     * @param topicId идентификатор обсуждения
     * @param model   модель
     * @return страница обсуждения
     */
    @GetMapping("/topic/{id}")
    public String showSingleTopicPage(@PathVariable("id") String topicId, Model model) {
        model.addAttribute("topic", topicService.getTopicById(topicId));
        model.addAttribute("comments", topicCommentsService.getCommentsListByTopicId(topicId));
        return "topic_page";
    }

    /**
     * Принимает данные от кнопки со страницы обсуждения и удаяляет его с помощью сервиса
     *
     * @param topicId идентификатор топика
     * @return перенаправление на страницу с обсуждениями
     */
    @PostMapping("/delete-topic")
    public String deleteTopic(@RequestParam("deleteTopicButton") String topicId) {
        topicService.deleteTopicAndCommentsByTopicId(topicId);
        return "redirect:/topics";
    }

}
