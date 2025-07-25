package lecsorter.config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Joiner;

import lecsorter.Constants;
import lecsorter.config.exceptions.AbbreviationAlreadyExistException;
import lecsorter.config.exceptions.DoubleAbbreviationException;
import lecsorter.config.exceptions.DoublePathException;
import lecsorter.config.exceptions.InvalidAbbreviationsConfigException;
import lecsorter.config.exceptions.InvalidConfigException;
import lecsorter.config.exceptions.InvalidPairsConfigException;
import lecsorter.config.exceptions.InvalidPathException;
import lecsorter.config.exceptions.PairAlreadyExistException;

// TODO: docs
public class LectionsSorterConfigsReader implements ConfigsReader {

    private AbbreviationsManager abbreviationsManager;
    private PairsManager pairsManager;
    private Path lectionsPath;
    private Path unsortedLectionsPath;

    private JSONObject jsonConfig;

    @Override
    public void readConfigs() throws IOException, InvalidConfigException {
        String configJson = new String(Files.readAllBytes(
                Paths.get(Constants.CONFIG_PATH)),
                StandardCharsets.UTF_8);
        jsonConfig = new JSONObject(configJson);

        readAbbreviations();
        readPairs();
        readPaths();
    }

    @Override
    public void readAbbreviations() throws IOException, InvalidAbbreviationsConfigException {
        LectionsSorterAbbreviationsManager result = new LectionsSorterAbbreviationsManager();

        JSONObject jsonAbbreviationsObject = jsonConfig.getJSONObject(Constants.ABBREVIATIONS_JSON_KEY);

        JSONArray jsonAbbreviationsArray;
        String shortName;
        for (String fullName : JSONObject.getNames(jsonAbbreviationsObject)) {
            jsonAbbreviationsArray = jsonAbbreviationsObject.getJSONArray(fullName);
            for (int i = 0; i < jsonAbbreviationsArray.length(); i++) {
                shortName = jsonAbbreviationsArray.getString(i);

                try {
                    result.addAbbreviation(fullName, shortName);
                } catch (AbbreviationAlreadyExistException e) {
                    throw new DoubleAbbreviationException(shortName);
                }
            }
        }

        abbreviationsManager = result;
    }

    @Override
    public void readPairs() throws IOException, InvalidPairsConfigException {
        LectionsSorterPairsManager result = new LectionsSorterPairsManager();

        JSONObject jsonPairs = jsonConfig.getJSONObject(Constants.PAIRS_JSON_KEY);

        Joiner joiner = Joiner.on(File.separator);
        JSONArray lectionPath;
        for (String lectionName : JSONObject.getNames(jsonPairs)) {
            lectionPath = jsonPairs.getJSONArray(lectionName);
            try {
                result.addPair(lectionName, joiner.join(lectionPath));
            } catch (PairAlreadyExistException e) {
                throw new DoublePathException(lectionName);
            }
        }

        pairsManager = result;
    }

    @Override
    public void readPaths() throws IOException, InvalidPathException {
        lectionsPath = Paths.get(jsonConfig.getString(Constants.LECTIONS_PATH_JSON_KEY));
        unsortedLectionsPath = Paths.get(jsonConfig.getString(Constants.UNSORTED_LECTIONS_PATH_JSON_KEY));
    }

    @Override
    public String getFullNameOf(String shortName) {
        return getAbbreviationsManager().getFullNameOf(shortName);
    }

    @Override
    public List<String> getShortNamesOf(String fullName) {
        return getAbbreviationsManager().getShortNamesOf(fullName);
    }

    @Override
    public Path getPathByName(String name) {
        return getPairsManager().getPathByName(name);
    }

    @Override
    public String getNameByPath(String path) {
        return getPairsManager().getNameByPath(path);
    }

    @Override
    public String getNameByPath(Path path) {
        return getPairsManager().getNameByPath(path);
    }

    @Override
    public Path getLectionsPath() {
        return lectionsPath;
    }

    @Override
    public Path getUnsortedLectionsPath() {
        return unsortedLectionsPath;
    }

    @Override
    public Iterable<String> getAllFullNames() {
        return getAbbreviationsManager().getAllFullNames();
    }

    @Override
    public Iterable<String> getAllShortNames() {
        return getAbbreviationsManager().getAllShortNames();
    }

    @Override
    public Iterable<String> getAllNames() {
        return getPairsManager().getAllNames();
    }

    @Override
    public Iterable<Path> getAllPaths() {
        return getPairsManager().getAllPaths();
    }

    protected AbbreviationsManager getAbbreviationsManager() {
        return abbreviationsManager;
    }

    protected PairsManager getPairsManager() {
        return pairsManager;
    }

}
