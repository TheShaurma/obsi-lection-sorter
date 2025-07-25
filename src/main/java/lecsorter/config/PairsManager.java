package lecsorter.config;

import java.nio.file.Path;

// TODO: docs
public interface PairsManager {

    Path getPathByName(String name);

    String getNameByPath(String path);

    String getNameByPath(Path path);

    Iterable<String> getAllNames();

    Iterable<Path> getAllPaths();

}
