package by.overone.it.repository;

import by.overone.it.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Репозиторий, который позволяет работать с сообщениями пользователей в базе данных
 */
@Transactional
public interface UserPostRepository extends JpaRepository<UserPost, String> {

}
