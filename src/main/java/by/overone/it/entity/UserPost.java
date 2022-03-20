package by.overone.it.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Сущность сообщений
 */

@Entity
@Getter
public class UserPost {

    /**
     * Уникальный идентификатор пользователя, который генерируется с помощью утилиты uuid.
     * Для избежания проблем сеттер для этого поля устанавливать не надо!
     * Автоматически генерируется при сохранении сущности в базу данных.
     */
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;
    /**
     * Текст сообщения
     */
    @Setter
    private String text;
    /**
     * Идентификатор пользователя, который отправил сообщение
     */
    @Setter
    private String ownerId;
    /**
     * Имя пользователя, который отправил сообщение
     */
    @Setter
    private String ownerUsername;
    /**
     * Время отправки сообщения
     */
    @Setter
    private LocalDateTime postTime;
}
