package lecsorter.config.exceptions;

// TODO: docs
public class ConfigsNotReadException extends Exception {

    @Override
    public String toString() {
        return "Configs weren't read yet. You should call configReaderObject.readConfig() before.";
    }
}
