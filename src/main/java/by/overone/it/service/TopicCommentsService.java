package by.overone.it.service;

import by.overone.it.entity.TopicComments;
import by.overone.it.repository.TopicCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicCommentsService {

    @Autowired
    private TopicCommentsRepository repository;

    public void save(
            String topicId,
            String authorId,
            String authorUsername,
            String comment
    ) {
        TopicComments topicComments = new TopicComments();
        topicComments.setAuthorId(authorId);
        topicComments.setAuthorUsername(authorUsername);
        topicComments.setTopicId(topicId);
        topicComments.setComment(comment);
        topicComments.setCreatedDate(LocalDateTime.now());
        save(topicComments);
    }

    private void save(TopicComments topicComments) {
        repository.save(topicComments);
    }

    public List<TopicComments> getCommentsListByTopicId(String topicId) {
        return repository.findByTopicId(topicId);
    }

    public void deleteCommentById(String id) {
        repository.deleteById(id);
    }
}
