package lecsorter.config;

import java.util.List;

// TODO: docs
public interface AbbreviationsManager {

    String getFullNameOf(String shortName);

    List<String> getShortNamesOf(String fullName);

    Iterable<String> getAllFullNames();

    Iterable<String> getAllShortNames();

}
