package lecsorter.lections;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lecsorter.config.ConfigsReader;
import lecsorter.config.LectionsSorterConfigsReader;
import lecsorter.config.exceptions.InvalidConfigException;

// TODO: docs
public class LectionsManager {

    protected ConfigsReader configsReader;

    public LectionsManager() throws IOException, InvalidConfigException {
        configsReader = new LectionsSorterConfigsReader();
        configsReader.readConfigs();
    }

    public void sortLections() throws IOException, InvalidConfigException {
        for (Lection lection : getAllLections()) {
            Path resultFilePath = lection.getSubjectPath().resolve(lection.getFinalFileName());
            Files.write(resultFilePath, lection.getLection().getBytes());
        }
    }

    protected Iterable<Lection> getAllLections() throws IOException, InvalidConfigException {
        List<Lection> result = new ArrayList<>();

        // collect to iterable all files from unsorted lections folder
        Iterable<File> lectionsFiles;
        try (Stream<File> filesStream = Files.walk(configsReader.getUnsortedLectionsPath())
                .filter(Files::isRegularFile)
                .map(Path::toFile)) {
            lectionsFiles = filesStream.toList();
        }

        // TODO: process only lection files
        for (File i : lectionsFiles) {
            result.add(new LectionsSorterLection(i, configsReader));
        }
        return result;
    }

    protected class LectionsSorterLection implements Lection {

        private final String finalFileName;
        private final LocalDate date;
        private final String lection;
        private final Path subjectPath;

        // TODO: control is this a lection file or not
        public LectionsSorterLection(File lectionFile, ConfigsReader configsReader)
                throws IOException, InvalidConfigException {
            configsReader.readConfigs();

            String fileName = lectionFile.getName();
            finalFileName = fileName.substring(0, configsReader.getSeparatorIndex()) + ".md";

            int year = Integer.parseInt(fileName.substring(
                    configsReader.getFirstYearIndex(),
                    configsReader.getLastYearIndex() + 1));
            int month = Integer.parseInt(fileName.substring(
                    configsReader.getFirstMonthIndex(),
                    configsReader.getLastMonthIndex() + 1));
            int day = Integer.parseInt(fileName.substring(
                    configsReader.getFirstDayIndex(),
                    configsReader.getLastDayIndex() + 1));
            date = LocalDate.of(year, month, day);

            lection = com.google.common.io.Files.asCharSource(lectionFile, StandardCharsets.UTF_8).read();

            subjectPath = configsReader.getLectionsPath()
                    .resolve(configsReader.getPathBySubjectName(
                            configsReader.getFullNameOf(fileName.substring(
                                    configsReader.getSeparatorIndex() + 1,
                                    fileName.lastIndexOf('.')))));
        }

        @Override
        public String getFinalFileName() {
            return finalFileName;
        }

        @Override
        public LocalDate getDate() {
            return date;
        }

        @Override
        public String getLection() {
            return lection;
        }

        @Override
        public Path getSubjectPath() {
            return subjectPath;
        }

    }

}
