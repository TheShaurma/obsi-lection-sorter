package lecsorter;

import lecsorter.config.LectionsSorterConfigsReader;
import lecsorter.lections.LectionsManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(new LectionsSorterConfigsReader().getUnsortedLectionsPath());
        new LectionsManager().sortLections();
    }
}
