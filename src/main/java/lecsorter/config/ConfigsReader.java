package lecsorter.config;

import java.io.IOException;

import lecsorter.config.exceptions.InvalidConfigException;

// TODO: docs
public interface ConfigsReader extends AbbreviationsReader, PairsReader, PathsReader {

    void readConfigs() throws IOException, InvalidConfigException;
}
