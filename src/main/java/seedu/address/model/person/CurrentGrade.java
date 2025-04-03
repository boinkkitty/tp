package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.ColorUtil.getGradeHexColor;

/**
 * Represents a Person's current grade in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCurrentGrade(String)}
 */
public class CurrentGrade {

    public static final String MESSAGE_CONSTRAINTS = "Current Grade must be one of the following:\n"
            + "  - A-F with an optional + or -, e.g., A, B+, C-\n"
            + "  - Pass/Fail style codes: CS, CU, S, U, Pass, Fail\n"
            + "  - Or left blank.";

    /*
     * The first character of the Current Grade must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?:[A-F][+-]?|CS|CU|S|U|PASS|FAIL|)$";

    public final String value;
    public final String color;

    /**
     * Constructs a {@code CurrentGrade} without specifying the grade (defaults to empty string).
     */
    public CurrentGrade() {
        this.value = "";
        this.color = getGradeHexColor("");
    }

    /**
     * Constructs a {@code CurrentGrade}.
     *
     * @param currentGrade A valid current grade.
     */
    public CurrentGrade(String currentGrade) {
        requireNonNull(currentGrade);
        checkArgument(isValidCurrentGrade(currentGrade), MESSAGE_CONSTRAINTS);
        this.value = currentGrade;
        this.color = getGradeHexColor(getCurrentGradeLetter());
    }

    /**
     * Extracts the first letter of the given grade string.
     *
     * @return the first letter of the grade if present, otherwise an empty string
     */
    public String getCurrentGradeLetter() {
        return value.trim().isEmpty() ? "" : value.substring(0, 1);
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
