package lecsorter.config;

import java.io.IOException;

import lecsorter.config.exceptions.InvalidPairsConfigException;

// TODO: docs
public interface PairsReader {

    void readPairs() throws IOException, InvalidPairsConfigException;

    PairsManager getPairsManager();

}
