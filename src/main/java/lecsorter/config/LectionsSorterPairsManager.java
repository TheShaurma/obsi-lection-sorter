package lecsorter.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import lecsorter.config.exceptions.PairAlreadyExistException;

// TODO: docs
public class LectionsSorterPairsManager implements PairsManager {

    private final Map<String, Path> namePathPairs;
    private final Map<Path, String> pathNamePairs;

    public LectionsSorterPairsManager() {
        namePathPairs = new HashMap<>();
        pathNamePairs = new HashMap<>();
    }

    @Override
    public Path getPathByName(String name) {
        return namePathPairs.get(name);
    }

    @Override
    public String getNameByPath(String path) {
        return getNameByPath(Paths.get(path));
    }

    @Override
    public String getNameByPath(Path path) {
        return pathNamePairs.get(path);
    }

    public void addPair(String name, String path) throws PairAlreadyExistException {
        addPair(name, Paths.get(path));
    }

    public void addPair(String name, Path path) throws PairAlreadyExistException {
        if (namePathPairs.containsKey(name)) {
            throw new PairAlreadyExistException(name, path.toString());
        }

        namePathPairs.put(name, path);
        pathNamePairs.put(path, name);
    }

    @Override
    public Iterable<String> getAllNames() {
        return namePathPairs.keySet();
    }

    @Override
    public Iterable<Path> getAllPaths() {
        return pathNamePairs.keySet();
    }

}
