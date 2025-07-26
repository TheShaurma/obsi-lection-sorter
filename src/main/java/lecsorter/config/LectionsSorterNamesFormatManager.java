package lecsorter.config;

import org.json.JSONObject;

import lecsorter.config.exceptions.InvalidLectionsNamesConfigException;

public class LectionsSorterNamesFormatManager implements NamesFormatManager {

    private final int separatorIndex;
    private final String separatorFormat;
    private final int firstYearIndex;
    private final int lastYearIndex;
    private final int firstMonthIndex;
    private final int lastMonthIndex;
    private final int firstDayIndex;
    private final int lastDayIndex;

    public LectionsSorterNamesFormatManager(JSONObject jsonNamesFormat) throws InvalidLectionsNamesConfigException {
        // TODO: check if jsonNamesFormat valid
        this.separatorIndex = jsonNamesFormat
                .getInt(Constants.UNSORTED_LECTION_FILE_NAME_SEPARATOR_INDEX_JSON_KEY);
        this.separatorFormat = jsonNamesFormat
                .getString(Constants.UNSORTED_LECTION_FILE_NAME_SEPARATOR_FORMAT_JSON_KEY);
        this.firstYearIndex = jsonNamesFormat
                .getInt(Constants.LECTION_FILE_NAME_FIRST_YEAR_INDEX_JSON_KEY);
        this.lastYearIndex = jsonNamesFormat
                .getInt(Constants.LECTION_FILE_NAME_LAST_YEAR_INDEX_JSON_KEY);
        this.firstMonthIndex = jsonNamesFormat
                .getInt(Constants.LECTION_FILE_NAME_FIRST_MONTH_INDEX_JSON_KEY);
        this.lastMonthIndex = jsonNamesFormat
                .getInt(Constants.LECTION_FILE_NAME_LAST_MONTH_INDEX_JSON_KEY);
        this.firstDayIndex = jsonNamesFormat
                .getInt(Constants.LECTION_FILE_NAME_FIRST_DAY_INDEX_JSON_KEY);
        this.lastDayIndex = jsonNamesFormat
                .getInt(Constants.LECTION_FILE_NAME_LAST_DAY_INDEX_JSON_KEY);
    }

    @Override
    public int getSeparatorIndex() {
        return separatorIndex;
    }

    @Override
    public String getSeparatorFormat() {
        return separatorFormat;
    }

    @Override
    public int getFirstYearIndex() {
        return firstYearIndex;
    }

    @Override
    public int getLastYearIndex() {
        return lastYearIndex;
    }

    @Override
    public int getFirstMonthIndex() {
        return firstMonthIndex;
    }

    @Override
    public int getLastMonthIndex() {
        return lastMonthIndex;
    }

    @Override
    public int getFirstDayIndex() {
        return firstDayIndex;
    }

    @Override
    public int getLastDayIndex() {
        return lastDayIndex;
    }
}
