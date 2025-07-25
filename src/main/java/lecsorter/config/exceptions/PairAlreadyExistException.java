package lecsorter.config.exceptions;

// TODO: docs
public class PairAlreadyExistException extends Exception {

    private final String name;
    private final String path;

    public PairAlreadyExistException(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public String toString() {
        return String.format("Pair %s:%s is already exist.", getName(), getPath());
    }

    private Object getName() {
        return name;
    }

    public Object getPath() {
        return path;
    }

}
