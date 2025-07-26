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

import lecsorter.config.exceptions.AbbreviationAlreadyExistException;
import lecsorter.config.exceptions.DoubleAbbreviationException;
import lecsorter.config.exceptions.DoublePathException;
import lecsorter.config.exceptions.InvalidAbbreviationsConfigException;
import lecsorter.config.exceptions.InvalidConfigException;
import lecsorter.config.exceptions.InvalidLectionsNamesConfigException;
import lecsorter.config.exceptions.InvalidPairsConfigException;
import lecsorter.config.exceptions.InvalidPathException;
import lecsorter.config.exceptions.PairAlreadyExistException;

// TODO: docs
public class LectionsSorterConfigsReader implements ConfigsReader {

    protected AbbreviationsManager abbreviationsManager;
    protected PairsManager pairsManager;
    protected NamesFormatManager formatManager;
    private Path lectionsPath;
    private Path unsortedLectionsPath;
    private Path backupsPath;

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
        readFormat();
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
        JSONObject jsonPaths = jsonConfig.getJSONObject(Constants.PATHS_JSON_KEY);

        lectionsPath = Paths.get(jsonPaths.getString(Constants.LECTIONS_PATH_JSON_KEY));
        unsortedLectionsPath = Paths.get(jsonPaths.getString(Constants.UNSORTED_LECTIONS_PATH_JSON_KEY));
        backupsPath = Paths.get(jsonPaths.getString(Constants.BACKUPS_PATH_JSON_KEY));
    }

    @Override
    public void readFormat() throws InvalidLectionsNamesConfigException {
        formatManager = new LectionsSorterNamesFormatManager(jsonConfig
                .getJSONObject(Constants.LECTIONS_NAMES_FORMAT_JSON_KEY));
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
    public Path getPathBySubjectName(String name) {
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
    public Path getBackupsPath() {
        return backupsPath;
    }

    @Override
    public int getSeparatorIndex() {
        return formatManager.getSeparatorIndex();
    }

    @Override
    public String getSeparatorFormat() {
        return formatManager.getSeparatorFormat();
    }

    @Override
    public int getFirstYearIndex() {
        return formatManager.getFirstYearIndex();
    }

    @Override
    public int getLastYearIndex() {
        return formatManager.getLastYearIndex();
    }

    @Override
    public int getFirstMonthIndex() {
        return formatManager.getFirstMonthIndex();
    }

    @Override
    public int getLastMonthIndex() {
        return formatManager.getLastMonthIndex();
    }

    @Override
    public int getFirstDayIndex() {
        return formatManager.getFirstDayIndex();
    }

    @Override
    public int getLastDayIndex() {
        return formatManager.getLastDayIndex();
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
    public Iterable<Path> getAllAssignedLectionsPaths() {
        return getPairsManager().getAllPaths();
    }

    protected AbbreviationsManager getAbbreviationsManager() {
        return abbreviationsManager;
    }

    protected PairsManager getPairsManager() {
        return pairsManager;
    }

}
