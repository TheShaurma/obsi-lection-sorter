package lecsorter.config;

import lecsorter.config.exceptions.InvalidLectionsNamesConfigException;

public interface NamesFormatReader {

    void readFormat() throws InvalidLectionsNamesConfigException;

    int getSeparatorIndex();

    String getSeparatorFormat();

    int getFirstYearIndex();

    int getLastYearIndex();

    int getFirstMonthIndex();

    int getLastMonthIndex();

    int getFirstDayIndex();

    int getLastDayIndex();

}
