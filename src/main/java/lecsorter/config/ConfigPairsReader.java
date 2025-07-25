package lecsorter.config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Joiner;

import lecsorter.Constants;
import lecsorter.config.exceptions.DoublePathException;
import lecsorter.config.exceptions.InvalidPairsConfigException;
import lecsorter.config.exceptions.PairAlreadyExistException;

// TODO: docs
public class ConfigPairsReader implements PairsReader {

    private PairsManager pairsManager;

    @Override
    public void readPairs() throws IOException, InvalidPairsConfigException {
        LectionsSorterPairsManager result = new LectionsSorterPairsManager();

        String configJson = new String(Files.readAllBytes(Paths.get(Constants.CONFIG_PATH)), StandardCharsets.UTF_8);
        JSONObject jsonPairs = new JSONObject(configJson).getJSONObject(Constants.PAIRS_JSON_KEY);

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
    public PairsManager getPairsManager() {
        return pairsManager;
    }

}
