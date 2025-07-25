package lecsorter.config.exceptions;

// TODO: docs
public class DoubleAbbreviationException extends InvalidAbbreviationsConfigException {

    private final String shortName;

    public DoubleAbbreviationException(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return String.format("More then 1 full name for the short name \"%s\"", getShortName());
    }

    public String getShortName() {
        return shortName;
    }

}
