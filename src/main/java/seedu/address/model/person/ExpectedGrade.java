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
            "Expected Grade allows 1 Letter Grade from A to F "
                    + "with 1 + / - symbols, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?:|[A-F][+-]?|)$";

    public final String value;
    public final String color;

    /**
     * Constructs a {@code Expected Grade}.
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
