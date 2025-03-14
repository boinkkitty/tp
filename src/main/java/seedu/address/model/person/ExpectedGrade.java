package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Expected Grade in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidExpectedGrade(String)}
 */
public class ExpectedGrade {

    public static final String MESSAGE_CONSTRAINTS =
            "Expected Grades should only contain a single upper case alphabet";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(^$|^[^\s].*)";

    public final String value;

    /**
     * Constructs a {@code Expected Grade}.
     *
     * @param expectedGrade A valid expected grade.
     */
    public ExpectedGrade(String expectedGrade) {
        requireNonNull(expectedGrade);
        checkArgument(isValidExpectedGrade(expectedGrade), MESSAGE_CONSTRAINTS);
        value = expectedGrade;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidExpectedGrade(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if there is a grade
     */
    public boolean isEmptyExpectedGrade() {
        return value.isEmpty();
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
