package lecsorter.config;

import java.io.IOException;

import lecsorter.config.exceptions.ConfigsNotReadException;
import lecsorter.config.exceptions.InvalidConfigException;

// TODO: docs
public class LectionsSorterConfigReader implements ConfigReader {

    private final PairsReader pairsReader;
    private final AbbreviationsReader abbreviationsReader;

    public LectionsSorterConfigReader() {
        pairsReader = new ConfigPairsReader();
        abbreviationsReader = new ConfigAbbreviationsReader();
    }

    @Override
    public void readConfigs() throws IOException, InvalidConfigException {
        pairsReader.readPairs();
        abbreviationsReader.readAbbreviations();
    }

    @Override
    public PairsManager getPairsManager() throws ConfigsNotReadException {
        return pairsReader.getPairsManager();
    }

    @Override
    public AbbreviationsManager getAbbreviationsManager() throws ConfigsNotReadException {
        return abbreviationsReader.getAbbreviationsManager();
    }

}
