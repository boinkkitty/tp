package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's current grade in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCurrentGrade(String)}
 */
public class CurrentGrade {

    public static final String MESSAGE_CONSTRAINTS = "Current Grade can take any captitalised Letter Grade from A to F "
            + "with + / - symbols, and it should not be blank";

    /*
     * The first character of the Current Grade must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[A-F+-]+$";

    public final String value;

    /**
     * Constructs an {@code Current Grade}.
     *
     * @param currentGrade A valid current grade.
     */
    public CurrentGrade(String currentGrade) {
        requireNonNull(currentGrade);
        checkArgument(isValidCurrentGrade(currentGrade), MESSAGE_CONSTRAINTS);
        value = currentGrade;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidCurrentGrade(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CurrentGrade)) {
            return false;
        }

        CurrentGrade otherCurrentGrade = (CurrentGrade) other;
        return value.equals(otherCurrentGrade.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
