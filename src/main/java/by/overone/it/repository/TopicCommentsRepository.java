package by.overone.it.repository;

import by.overone.it.entity.TopicComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface TopicCommentsRepository extends JpaRepository<TopicComments, String> {

    List<TopicComments> findByTopicId(String topicId);
}
