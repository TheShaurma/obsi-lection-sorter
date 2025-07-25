package lecsorter.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import lecsorter.Constants;
import lecsorter.config.exceptions.AbbreviationAlreadyExistException;
import lecsorter.config.exceptions.DoubleAbbreviationException;
import lecsorter.config.exceptions.InvalidAbbreviationsConfigException;

// TODO: docs
public class ConfigAbbreviationsReader implements AbbreviationsReader {

    private AbbreviationsManager abbreviationsManager;

    @Override
    public void readAbbreviations() throws IOException, InvalidAbbreviationsConfigException {
        LectionsSorterAbbreviationsManager result = new LectionsSorterAbbreviationsManager();

        String configJson = new String(Files.readAllBytes(Paths.get(Constants.CONFIG_PATH)), StandardCharsets.UTF_8);
        JSONObject jsonAbbreviationsObject = new JSONObject(configJson).getJSONObject(Constants.ABBREVIATIONS_JSON_KEY);

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
    public AbbreviationsManager getAbbreviationsManager() {
        return abbreviationsManager;
    }

}
