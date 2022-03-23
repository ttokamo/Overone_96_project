package by.overone.it.service;

import by.overone.it.entity.Topic;
import by.overone.it.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public void save(
            String authorId,
            String authorUsername,
            String topicName,
            String message
    ) {
        Topic topic = new Topic();
        topic.setAuthorId(authorId);
        topic.setAuthor(authorUsername);
        topic.setTopicName(topicName);
        topic.setMessage(message);
        topic.setCreateDate(LocalDateTime.now());
        save(topic);
    }

    private void save(Topic topic) {
        topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(String id) {
        return topicRepository.findById(id);
    }
}