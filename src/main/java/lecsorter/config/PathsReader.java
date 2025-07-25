package lecsorter.config;

import java.io.IOException;
import java.nio.file.Path;

import lecsorter.config.exceptions.InvalidPathException;

// TODO: docs
public interface PathsReader {

    void readPaths() throws IOException, InvalidPathException;

    Path getLectionsPath();

    Path getUnsortedLectionsPath();

}
