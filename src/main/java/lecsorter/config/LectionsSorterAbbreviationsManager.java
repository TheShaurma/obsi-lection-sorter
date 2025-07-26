package lecsorter.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecsorter.config.exceptions.AbbreviationAlreadyExistException;

// TODO: docs
public class LectionsSorterAbbreviationsManager implements AbbreviationsManager {

    private final Map<String, List<String>> fullShortMap;
    private final Map<String, String> shortFullMap;

    public LectionsSorterAbbreviationsManager() {
        fullShortMap = new HashMap<>();
        shortFullMap = new HashMap<>();
    }

    @Override
    public String getFullNameOf(String shortName) {
        if (fullShortMap.containsKey(shortName)) {
            return shortName;
        } else {
            return shortFullMap.get(shortName);
        }
    }

    @Override
    public List<String> getShortNamesOf(String fullName) {
        return fullShortMap.get(fullName);
    }

    public void addAbbreviation(String fullName, String shortName) throws AbbreviationAlreadyExistException {
        if (shortFullMap.containsKey(shortName)) {
            throw new AbbreviationAlreadyExistException(fullName, shortName);
        }

        if (!fullShortMap.containsKey(fullName)) {
            fullShortMap.put(fullName, new ArrayList<>());
        }

        fullShortMap.get(fullName).add(shortName);
        shortFullMap.put(shortName, fullName);
    }

    @Override
    public Iterable<String> getAllFullNames() {
        return fullShortMap.keySet();
    }

    @Override
    public Iterable<String> getAllShortNames() {
        return shortFullMap.keySet();
    }

}
