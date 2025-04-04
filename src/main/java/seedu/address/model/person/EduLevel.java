package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's education level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEduLevel(String)}
 */
public class EduLevel {


    private static final String[] VALID_EDU_LEVELS = {"Primary", "Secondary", "Diploma", "Bachelor", "Master", "PhD"};
    public static final String MESSAGE_CONSTRAINTS =
            "Education level should not be blank and should be one of the predefined levels: "
                    + String.join(", ", VALID_EDU_LEVELS) + ".";

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
     * Returns true if the string is a valid education level.
     */
    public static boolean isValidEduLevel(String eduLevel) {
        if (eduLevel.isEmpty()) {
            return true;
        }
        for (String validLevel : VALID_EDU_LEVELS) {
            if (validLevel.equalsIgnoreCase(eduLevel)) {
                return true;
            }
        }
        return false;
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
        return value.equalsIgnoreCase(otherEduLevel.value);
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
