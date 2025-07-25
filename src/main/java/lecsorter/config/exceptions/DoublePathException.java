package lecsorter.config.exceptions;

// TODO: docs
public class DoublePathException extends InvalidPairsConfigException {

    private final String name;

    public DoublePathException(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("More than 1 path for %s lections.", getName());
    }

    public Object getName() {
        return name;
    }

}
