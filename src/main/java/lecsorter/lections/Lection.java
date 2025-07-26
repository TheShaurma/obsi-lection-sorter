package lecsorter.lections;

import java.nio.file.Path;
import java.time.LocalDate;

// TODO: docs
public interface Lection {

    String getFinalFileName();

    LocalDate getDate();

    String getLection();

    Path getSubjectPath();

}
