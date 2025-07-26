package lecsorter.config;

import java.io.IOException;

import lecsorter.config.exceptions.InvalidConfigException;

// TODO: docs
public interface ConfigsReader extends AbbreviationsReader, PairsReader, PathsReader, NamesFormatReader {

    void readConfigs() throws IOException, InvalidConfigException;
}
