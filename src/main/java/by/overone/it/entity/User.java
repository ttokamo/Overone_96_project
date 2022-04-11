package by.overone.it.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Сущность пользователя
 */
@Entity
@Getter
public class User {
    /**
     * Уникальный идентификатор пользователя, который генерируется с помощью утилиты uuid.
     * Для избежания проблем сеттер для этого поля устанавливать не надо!
     * Автоматически генерируется при сохранении сущности в базу данных.
     */
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;
    @Setter
    private String username;
    @Setter
    private String password;
    /**
     * Роль пользователя в системе. От роли зависит функционал, которым может
     * пользоваться User.
     */
    @Setter
    private String role;
    /**
     * Статус аккаунта пользователя. Влияет на работоспособность аккаунта.
     * Например: Активен или Заблокирован
     */
    @Setter
    private String status;
    /**
     * Путь до каталога, где хранится изображение, которое отображается на странице профиля (Аватар).
     */
    @Setter
    private String pathToImage;
}
