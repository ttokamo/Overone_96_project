package by.overone.it.util;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Класс для работы с файлами обсуждений
 */
public class FileWorker {

    /**
     * Сохраняет картинку обсуждения
     *
     * @param file изображение
     */
    @SneakyThrows
    public static void saveFileFromTopic(MultipartFile file) {
        file.transferTo(PathsNames.getPathToTopicImages(file.getOriginalFilename()));
    }

    /**
     * Сохраняет картинку комментария
     *
     * @param file изображение
     */
    @SneakyThrows
    public static void saveFileFromComments(MultipartFile file) {
        file.transferTo(PathsNames.getPathToTopicCommentsImages(file.getOriginalFilename()));
    }

    /**
     * Удаляет изображение по указанному пути
     *
     * @param path путь до изображения
     */
    @SneakyThrows
    public static void deleteFile(Path path) {
        File file = new File(String.valueOf(path));

        if (!file.delete()) {
            throw new IOException("Error during deleting image");
        }
    }
}
