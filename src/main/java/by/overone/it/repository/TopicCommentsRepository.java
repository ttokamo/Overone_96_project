package by.overone.it.repository;

import by.overone.it.entity.TopicComments;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Репозиторий, который работает с бд для комментариев
 */
@Transactional
public interface TopicCommentsRepository extends JpaRepository<TopicComments, String> {

    List<TopicComments> findByTopicId(String topicId);

    void deleteByTopicId(String topicId);
}
