package lecsorter.config;

import java.io.IOException;

import lecsorter.config.exceptions.ConfigsNotReadException;
import lecsorter.config.exceptions.InvalidConfigException;

// TODO: docs
public interface ConfigReader {

    void readConfigs() throws IOException, InvalidConfigException;

    PairsManager getPairsManager() throws ConfigsNotReadException;

    AbbreviationsManager getAbbreviationsManager() throws ConfigsNotReadException;
}
