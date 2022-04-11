package by.overone.it.service;

import by.overone.it.entity.TopicComments;
import by.overone.it.repository.TopicCommentsRepository;
import by.overone.it.util.FileWorker;
import by.overone.it.util.PathsNames;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicCommentsService {

    @Autowired
    private TopicCommentsRepository repository;

    /**
     * Сохраняет сущность комментария в базе данных
     *
     * @param topicId            идентификатор обсуждения
     * @param authorId           идентификатор автора
     * @param authorUsername     имя пользователя автора
     * @param pathToCommentImage путь до изображения
     * @param comment            комментарий
     */
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
        topicComments.setImageFileName(pathToCommentImage);
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


    /**
     * Удаляет комментарий и его изображение
     *
     * @param id идентификатор комментария
     */
    @SneakyThrows
    @Transactional
    public void deleteCommentAndCommentImageById(String id) {
        TopicComments comment = getTopicById(id);

        if (!comment.getImageFileName().isEmpty()) {
            FileWorker.deleteFile(
                    PathsNames.getPathToTopicCommentsImages(
                            comment.getImageFileName()
                    ));
        }

        repository.deleteById(id);
    }
}
