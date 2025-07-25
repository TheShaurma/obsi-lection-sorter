package lecsorter.config.exceptions;

// TODO: docs
public class AbbreviationAlreadyExistException extends Exception {

    private final String fullName;
    private final String shortName;

    public AbbreviationAlreadyExistException(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return String.format("Abbreviation %s->%s is already exist.", getFullName(), getShortName());
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

}
