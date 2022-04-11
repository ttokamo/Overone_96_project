package by.overone.it.util;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс дял быстрого доступа к путям изображений
 */
public class PathsNames {


    /**
     * Статический метод для быстрого доступа к изображению из комментария
     *
     * @param fileName имя файла
     * @return путь до изображения
     */
    public static Path getPathToTopicCommentsImages(String fileName) {
        return Paths.get("src", "main", "webapp", "topic-images", "topic-comments-images", fileName);
    }

    /**
     * Статический метод для быстрого доступа к изображению обсуждения
     *
     * @param fileName имя файла
     * @return путь до изображения
     */
    public static Path getPathToTopicImages(String fileName) {
        return Paths.get("src", "main", "webapp", "topic-images", fileName);
    }
}
