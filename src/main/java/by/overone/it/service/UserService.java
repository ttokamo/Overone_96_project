package by.overone.it.service;

import by.overone.it.encoder.PasswordEncoder;
import by.overone.it.entity.User;
import by.overone.it.enums.StatusEnums;
import by.overone.it.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с пользователями, который является связующим между веб частью и БД частью
 */
@Service
public class UserService {

    /**
     * Репозиторий для работы с пользователями в БД
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Метод, который создает и заполняет сущность пользователя. Далее вызывает метод сохранения пользователя в БД.
     * Кроме установленных параметров так же автоматически устанавливает статус аккаунта (ACTIVE), путь до
     * стандартной картинки профиля и автоматически шифрует пароль.
     * @param username имя пользователя
     * @param password пароль пользователя
     * @param role роль пользователя
     */
    public void save(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setRole(role);
        user.setPathToImage("user-images/user-profile-img.png");
        user.setStatus(StatusEnums.ACTIVE.name());
        user.setPassword(PasswordEncoder.encodePassword(password));
        save(user);
    }

    /**
     * Метод сохранения пользователя в базу данных
     * @param user объект типа User
     */
    private void save(User user) {
        userRepository.save(user);
    }

    /**
     * Обновляет статус аккаунта пользователя по id
     * @param status новый статус
     * @param id идентификатор
     */
    public void updateUserStatusById(String status, String id) {
        userRepository.updateUserStatusById(status, id);
    }

    /**
     * Поиск пользователя по его имени пользователя(username)
     * @param username имя пользователя
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Метод, который возвращает весь список пользователей
     * @return список пользователей
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Метод, который находит пользователя по его id
     * @param id идентификатор пользователя
     */
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Метод, который удаляет пользователя по его id
     * @param id идентификатор пользователя
     */
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    /**
     * Обновляет путь до изображения пользователя
     * @param path путь до картинки
     * @param id идентификатор
     */
    public void updatePathToImageById(String path, String id) {
        userRepository.updateUserPathToImageById(path, id);
    }

    /**
     * Поиск имени пользователя по введенному значению
     * @param inputText введенный текст
     * @return список пользователей, у которых имя пользователя подходит под введенные параметры
     */
    public List<User> searchUserUsernameByInputText(String inputText) {
        return userRepository.searchUserUsernameByInputText(inputText);
    }
}
