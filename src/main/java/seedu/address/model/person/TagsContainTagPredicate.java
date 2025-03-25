package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the {@code Tags} given.
 */
public class TagsContainTagPredicate implements Predicate<Person> {
    private final Set<Tag> targetTags; // Change targetTags to be a Set<String>

    /**
     * Constructor accepting a Set of target tags.
     *
     * @param targetTags Set of tags to filter the persons.
     */
    public TagsContainTagPredicate(Set<Tag> targetTags) {
        this.targetTags = targetTags;
    }

    /**
     * Tests that the tags of a {@code Person} contain any of the {@code targetTags}.
     *
     * @param person Person whose tags are tested.
     * @return True if the person has at least one tag matching the target tags.
     */
    @Override
    public boolean test(Person person) {
        // Check if any tag of the person matches a tag in the targetTags set
        return person.getTags().stream()
                .anyMatch(targetTags::contains); // Check in the Set
    }

    /**
     * Equality of TagsContainTagPredicate based on targetTags.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagsContainTagPredicate)) {
            return false;
        }

        TagsContainTagPredicate otherTagPredicate = (TagsContainTagPredicate) other;
        return targetTags.equals(otherTagPredicate.targetTags);
    }

    /**
     * Overridden toString method for debugging or logging.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("targetTags", targetTags).toString();
    }
}
