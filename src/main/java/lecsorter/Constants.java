package lecsorter;

import java.io.File;

// TODO: docs
public class Constants {

    private Constants() {
    }

    public static final String CONFIG_PATH = String.format(
            "configs%suser%spairs.json",
            File.separator,
            File.separator);

    public static final String PAIRS_JSON_KEY = "pairs";
    public static final String ABBREVIATIONS_JSON_KEY = "abbreviations";

}
