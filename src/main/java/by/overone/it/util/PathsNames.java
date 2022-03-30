package by.overone.it.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsNames {
    public static Path getPathToTopicCommentsImages(String fileName) {
        return Paths.get("src", "main", "webapp", "topic-images", "topic-comments-images", fileName);
    }

    public static Path getPathToTopicImages(String fileName) {
        return Paths.get("src", "main", "webapp", "topic-images", fileName);
    }
}
