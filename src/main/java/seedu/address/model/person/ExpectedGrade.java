package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.ColorUtil.getGradeHexColor;

/**
 * Represents a Person's Expected Grade in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidExpectedGrade(String)}
 */
public class ExpectedGrade {

    public static final String MESSAGE_CONSTRAINTS =
            "Expected Grade must be one of the following:\n"
                    + "  - A-F with an optional + or -, e.g., A, B+, C-\n"
                    + "  - Pass/Fail style codes: CS, CU, S, U, Pass, Fail\n"
                    + "  - Or left blank.";


    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?:[A-F][+-]?|CS|CU|S|U|Pass|Fail|)$";

    public final String value;
    public final String color;

    /**
     * Constructs an {@code ExpectedGrade} without specifying the grade (defaults to empty string).
     */
    public ExpectedGrade() {
        this.value = "";
        this.color = getGradeHexColor("");
    }

    /**
     * Constructs an {@code ExpectedGrade}.
     *
     * @param expectedGrade A valid expected grade.
     */
    public ExpectedGrade(String expectedGrade) {
        requireNonNull(expectedGrade);
        checkArgument(isValidExpectedGrade(expectedGrade), MESSAGE_CONSTRAINTS);
        this.value = expectedGrade;
        this.color = getGradeHexColor(getExpectedGradeLetter());
    }

    /**
     * Extracts first letter of the given grade string.
     *
     * @return The first letter of grade if present, otherwise an empty string.
     */
    public String getExpectedGradeLetter() {
        return value.trim().isEmpty() ? "" : value.substring(0, 1);
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidExpectedGrade(String test) {
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
        if (!(other instanceof ExpectedGrade)) {
            return false;
        }

        ExpectedGrade otherExpectedGrade = (ExpectedGrade) other;
        return value.equals(otherExpectedGrade.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
