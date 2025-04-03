package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's education level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEduLevel(String)}
 */
public class EduLevel {

    public static final int MAX_LENGTH = 30;

    public static final String MESSAGE_CONSTRAINTS =
            String.format("Education Level should only contain alphanumeric characters and "
                    + "spaces and must not exceed %d characters. ", MAX_LENGTH)
                    + "As Education Level is an optional field, it could also be blank.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]*";
    public final String value;

    /**
     * Constructs an {@code EduLevel} with a valid education level.
     *
     * @param eduLevel A valid education level.
     */
    public EduLevel(String eduLevel) {
        requireNonNull(eduLevel);
        checkArgument(isValidEduLevel(eduLevel), MESSAGE_CONSTRAINTS);
        this.value = eduLevel;
    }

    /**
     * Constructs an {@code EduLevel} without specifying the level (defaults to empty string).
     */
    public EduLevel() {
        this.value = "";
    }

    /**
     * Check length of education level against limit
     * @param test
     * @return Returns true if given education level exceeds max length
     */
    public static boolean isValidLength(String test) {
        return test.length() <= MAX_LENGTH;
    }

    /**
     * Returns true if the string is a valid education level.
     */
    public static boolean isValidEduLevel(String eduLevel) {
        return eduLevel.matches(VALIDATION_REGEX) && isValidLength(eduLevel);
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

        if (!(other instanceof EduLevel)) {
            return false;
        }

        EduLevel otherEduLevel = (EduLevel) other;
        return value.equals(otherEduLevel.value);
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
