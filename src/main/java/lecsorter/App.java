package lecsorter;

import lecsorter.config.ConfigReader;
import lecsorter.config.LectionsSorterConfigReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        ConfigReader configReader = new LectionsSorterConfigReader();
        configReader.readConfigs();

        for (String shortName : configReader.getPairsManager().getAllNames()) {
            System.out.println(String.format(
                    "%s -> %s",
                    shortName,
                    configReader.getPairsManager().getPathByName(shortName)));
        }

        System.out.println();

        for (String fullName : configReader.getAbbreviationsManager().getAllFullNames()) {
            System.out.println(String.format(
                    "%s -> %s",
                    fullName,
                    configReader.getAbbreviationsManager().getShortNamesOf(fullName)));
        }
    }
}
