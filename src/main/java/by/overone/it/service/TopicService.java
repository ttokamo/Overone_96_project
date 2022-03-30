package by.overone.it.service;

import by.overone.it.entity.Topic;
import by.overone.it.entity.TopicComments;
import by.overone.it.repository.TopicRepository;
import by.overone.it.util.FileWorker;
import by.overone.it.util.PathsNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicCommentsService topicCommentsService;

    public void save(
            String authorId,
            String authorUsername,
            String topicName,
            String pathToTopicImage,
            String message
    ) {
        Topic topic = new Topic();
        topic.setAuthorId(authorId);
        topic.setAuthor(authorUsername);
        topic.setTopicName(topicName);
        topic.setImageFileName(pathToTopicImage);
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

    @Transactional
    public void deleteTopicAndCommentsByTopicId(String topicId) {
        List<TopicComments> commentsList = topicCommentsService.getCommentsListByTopicId(topicId);

        for (TopicComments comment : commentsList) {
            topicCommentsService.deleteCommentAndCommentImageById(comment.getId());
        }

        Topic topic = topicRepository.getById(topicId);

        if (!topic.getImageFileName().isEmpty()) {
            FileWorker.deleteFile(
                    PathsNames.getPathToTopicImages(
                            topic.getImageFileName()
                    )
            );
        }

        topicRepository.deleteById(topicId);

    }
}
