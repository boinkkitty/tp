package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTag(String)}
 */
public class Tag {

    public static final String VALIDATION_REGEX = "^(\\p{Alnum}{1,10})(?:#([A-Fa-f0-9]{6}))?$";
    public static final int MAX_TAGS_IN_SET = 8;
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric and less than 10 characters "
            + "long, while its color codes should be 6 hexadecimal long (e.g. \"CS2040C#F2552E\")";
    public static final String MESSAGE_CONSTRAINTS_ADD_SET = "There can at most be " + MAX_TAGS_IN_SET
            + " unique tags per person.";
    public static final String MESSAGE_CONSTRAINTS_EDIT_SET = "The resulting set of tags should at most contain "
            + MAX_TAGS_IN_SET + " tags per person.";

    public final String fullTag;

    /**
     * Constructs a {@code Tag}.
     *
     * @param fullTag A valid full tag (e.g. `CS2040C#F2552E`).
     */
    public Tag(String fullTag) {
        requireNonNull(fullTag);
        checkArgument(isValidTag(fullTag), MESSAGE_CONSTRAINTS);
        this.fullTag = fullTag;
    }

    /**
     * Returns true if a given string is a valid tag format.
     */
    public static boolean isValidTag(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        // Tag is the same as long as the `tagName` (First half) is the same. `hexColor` (Second half) is ignored here.
        return fullTag.split("#")[0].equalsIgnoreCase(otherTag.fullTag.split("#")[0]);
    }

    @Override
    public int hashCode() {
        // NOTE: hashCode() is used primarily for determining duplicated content in a HashSet.
        return fullTag.split("#")[0].toLowerCase().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + fullTag + ']';
    }

}
