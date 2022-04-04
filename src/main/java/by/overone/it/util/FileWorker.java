package by.overone.it.util;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileWorker {

    @SneakyThrows
    public static void saveFileFromTopic(MultipartFile file) {
        file.transferTo(PathsNames.getPathToTopicImages(file.getOriginalFilename()));
    }

    @SneakyThrows
    public static void saveFileFromComments(MultipartFile file) {
        file.transferTo(PathsNames.getPathToTopicCommentsImages(file.getOriginalFilename()));
    }

    @SneakyThrows
    public static void deleteFile(Path path) {
        File file = new File(String.valueOf(path));

        if (!file.delete()) {
            throw new IOException("Error during deleting image");
        }
    }
}