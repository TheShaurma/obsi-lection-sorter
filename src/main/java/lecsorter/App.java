package lecsorter;

import lecsorter.config.ConfigsReader;
import lecsorter.config.LectionsSorterConfigsReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        ConfigsReader configReader = new LectionsSorterConfigsReader();
        configReader.readConfigs();

        for (String shortName : configReader.getAllNames()) {
            System.out.println(String.format(
                    "%s -> %s",
                    shortName,
                    configReader.getPathByName(shortName)));
        }

        System.out.println();

        for (String fullName : configReader.getAllFullNames()) {
            System.out.println(String.format(
                    "%s -> %s",
                    fullName,
                    configReader.getShortNamesOf(fullName)));
        }

        System.out.println();

        System.out.println(String.format("lections-path -> %s", configReader.getLectionsPath()));
        System.out.println(String.format("unsorted-lections-sorter -> %s", configReader.getUnsortedLectionsPath()));
    }
}
