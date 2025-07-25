package lecsorter.config;

import java.io.IOException;
import java.util.List;

import lecsorter.config.exceptions.InvalidAbbreviationsConfigException;

// TODO: docs
public interface AbbreviationsReader {

    void readAbbreviations() throws IOException, InvalidAbbreviationsConfigException;

    String getFullNameOf(String shortName);

    List<String> getShortNamesOf(String fullName);

    Iterable<String> getAllFullNames();

    Iterable<String> getAllShortNames();

}
