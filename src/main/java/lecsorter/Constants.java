package lecsorter;

import java.io.File;

// TODO: docs
public class Constants {

    private Constants() {
    }

    public static final String CONFIG_PATH = String.format(
            "configs%suser%sconfig.json",
            File.separator,
            File.separator);

    public static final String LECTIONS_PATH_JSON_KEY = "lections-path";
    public static final String UNSORTED_LECTIONS_PATH_JSON_KEY = "unsorted-lections-path";
    public static final String PAIRS_JSON_KEY = "pairs";
    public static final String ABBREVIATIONS_JSON_KEY = "abbreviations";

}
