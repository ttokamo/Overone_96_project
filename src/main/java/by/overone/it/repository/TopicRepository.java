package by.overone.it.repository;

import by.overone.it.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TopicRepository extends JpaRepository<Topic, String> {
}
