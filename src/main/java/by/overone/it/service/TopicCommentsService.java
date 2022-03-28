package by.overone.it.service;

import by.overone.it.entity.TopicComments;
import by.overone.it.repository.TopicCommentsRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            String pathToCommentImage,
            String comment
    ) {
        TopicComments topicComments = new TopicComments();
        topicComments.setAuthorId(authorId);
        topicComments.setAuthorUsername(authorUsername);
        topicComments.setTopicId(topicId);
        topicComments.setPathToCommentImage(pathToCommentImage);
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

    public TopicComments getTopicById(String id) {
        return repository.getById(id);
    }

    @SneakyThrows
    public void deleteCommentAndCommentImageById(String id) {
        TopicComments comment = getTopicById(id);
        Path path = Paths.get("src", "main", "webapp", comment.getPathToCommentImage());
        File file = new File(String.valueOf(path));
        if (file.delete()) {
            repository.deleteById(id);
        } else {
            throw new IOException("Error during deleting path to comment image");
        }
    }
}
