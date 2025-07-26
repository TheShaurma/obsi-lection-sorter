package lecsorter.config;

import java.io.File;

// TODO: docs
public class Constants {

    private Constants() {
    }

    public static final String CONFIG_PATH = String.format(
            "configs%suser%sconfig.json",
            File.separator,
            File.separator);

    public static final String PATHS_JSON_KEY = "paths";
    public static final String LECTIONS_PATH_JSON_KEY = "lections";
    public static final String UNSORTED_LECTIONS_PATH_JSON_KEY = "unsorted-lections";
    public static final String BACKUPS_PATH_JSON_KEY = "backups";

    public static final String PAIRS_JSON_KEY = "pairs";

    public static final String ABBREVIATIONS_JSON_KEY = "abbreviations";

    public static final String LECTIONS_NAMES_FORMAT_JSON_KEY = "lections-names-format";
    public static final String UNSORTED_LECTION_FILE_NAME_SEPARATOR_INDEX_JSON_KEY = "separator-index";
    public static final String UNSORTED_LECTION_FILE_NAME_SEPARATOR_FORMAT_JSON_KEY = "separator-format";
    public static final String LECTION_FILE_NAME_FIRST_YEAR_INDEX_JSON_KEY = "year-first-index";
    public static final String LECTION_FILE_NAME_LAST_YEAR_INDEX_JSON_KEY = "year-last-index";
    public static final String LECTION_FILE_NAME_FIRST_MONTH_INDEX_JSON_KEY = "month-first-index";
    public static final String LECTION_FILE_NAME_LAST_MONTH_INDEX_JSON_KEY = "month-last-index";
    public static final String LECTION_FILE_NAME_FIRST_DAY_INDEX_JSON_KEY = "day-first-index";
    public static final String LECTION_FILE_NAME_LAST_DAY_INDEX_JSON_KEY = "day-last-index";
}
