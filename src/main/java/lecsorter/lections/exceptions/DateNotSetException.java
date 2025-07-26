package lecsorter.lections.exceptions;

// TODO: docs
public class DateNotSetException extends LectionsSortingException {
    @Override
    public String toString() {
        return "Tried to create UnsortedLection, but date hadn't set.";
    }
}
