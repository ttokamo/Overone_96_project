package by.overone.it.repository;

import by.overone.it.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий, который позволяет работать с сущностью User
 */
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Поиск пользователя по его имени пользователя(username)
     * @param username имя пользователя
     */
    @Query("from User where username =:username")
    Optional<User> findUserByUsername(@Param("username") String username);

    /**
     * Обновляет статус аккаунта пользователя по id
     * @param status новый статус
     * @param id идентификатор
     */
    @Query("update User set status =:status where id =:id")
    @Modifying
    void updateUserStatusById(@Param("status") String status, @Param("id") String id);

    /**
     * Обновляет путь до изображения пользователя
     * @param path путь до картинки
     * @param id идентификатор
     */
    @Query("update User set pathToImage =:path where id =:id")
    @Modifying
    void updateUserPathToImageById(@Param("path") String path, @Param("id") String id);

    /**
     * Поиск имени пользователя по введенному значению
     * @param inputText введенный текст
     * @return список пользователей, у которых имя пользователя подходит под введенные параметры
     */
    @Query("from User where upper(username) like %:inputText%")
    List<User> searchUserUsernameByInputText(@Param("inputText") String inputText);
}
