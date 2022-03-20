package by.overone.it.service;

import by.overone.it.entity.UserPost;
import by.overone.it.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Сервис для работы с сообщениями пользователей, который является связующим между веб частью и БД частью
 */
@Service
public class UserPostService {

    /**
     * Репозиторий для работы с сообщениями в БД
     */
    @Autowired
    private UserPostRepository userPostRepository;

    /**
     * Метод, который создает и заполняет сущность сообщения. Далее вызывает метод сохранения сообщения в БД.
     * Кроме установленных параметров так же автоматически устанавливает время отправки сообщения
     * @param text текст сообщения
     * @param ownerId id отправителя
     * @param ownerUsername имя пользователя отправителя
     */
    public void save(String text, String ownerId, String ownerUsername) {
        UserPost userPost = new UserPost();
        userPost.setOwnerId(ownerId);
        userPost.setOwnerUsername(ownerUsername);
        userPost.setText(text);
        userPost.setPostTime(LocalDateTime.now());
        save(userPost);
    }

    /**
     * Метод, который сохраняет сообщение пользователя
     * @param post объект типа UserPost
     */
    private void save(UserPost post) {
        userPostRepository.save(post);
    }

    /**
     * Метод, который возвращает список всех сообщений в БД
     * @return список сообщений
     */
    public List<UserPost> getMessagesList() {
        return userPostRepository.findAll();
    }
}
