package lecsorter.config;

import java.io.IOException;
import java.nio.file.Path;

import lecsorter.config.exceptions.InvalidPairsConfigException;

// TODO: docs
public interface PairsReader {

    void readPairs() throws IOException, InvalidPairsConfigException;

    Path getPathBySubjectName(String name);

    String getNameByPath(String path);

    String getNameByPath(Path path);

    Iterable<String> getAllNames();

    Iterable<Path> getAllAssignedLectionsPaths();

}
