package lecsorter.config;

import java.io.IOException;

import lecsorter.config.exceptions.InvalidAbbreviationsConfigException;

// TODO: docs
public interface AbbreviationsReader {

    void readAbbreviations() throws IOException, InvalidAbbreviationsConfigException;

    AbbreviationsManager getAbbreviationsManager();

}
