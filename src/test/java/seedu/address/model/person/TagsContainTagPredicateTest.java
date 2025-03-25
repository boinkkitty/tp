package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class TagsContainTagPredicateTest {

    @Test
    public void equals() {
        Set<Tag> firstPredicateTagSet = Set.of(new Tag("first"));
        Set<Tag> secondPredicateTagSet = Set.of(new Tag("first"), new Tag("second"));

        // same object -> returns true
        assertTrue(firstPredicateTagSet.equals(firstPredicateTagSet));

        // same value -> returns true
        TagsContainTagPredicate firstPredicateCopy = new TagsContainTagPredicate(firstPredicateTagSet);
        assertEquals(firstPredicateCopy, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicateTagSet.equals(1));

        // null -> returns false
        assertFalse(firstPredicateTagSet.equals(null));

        // different person -> returns false
        assertFalse(firstPredicateTagSet.equals(secondPredicateTagSet));
    }

    @Test
    public void test_tagsContainTag_returnsTrue() {
        // One tag
        TagsContainTagPredicate predicate = new TagsContainTagPredicate(Set.of(new Tag("Friend")));
        assertTrue(predicate.test(new PersonBuilder().withTags("Friend", "Classmates").build()));

        // Multiple tags
        predicate = new TagsContainTagPredicate(Set.of(new Tag("Friend"), new Tag("Classmates")));
        assertTrue(predicate.test(new PersonBuilder().withTags("Friend", "Classmates").build()));
    }

    @Test
    public void test_tagsContainTag_returnsFalse() {
        TagsContainTagPredicate predicate = new TagsContainTagPredicate(Set.of(new Tag("enemies")));
        assertFalse(predicate.test(new PersonBuilder().withTags("Friend", "Classmates").build()));
    }

    @Test
    public void toStringMethod() {
        Set<Tag> tags = Set.of(new Tag("tag1"), new Tag("tag2"));
        TagsContainTagPredicate predicate = new TagsContainTagPredicate(tags);
        String expected = TagsContainTagPredicate.class.getCanonicalName() + "{targetTags=" + tags + "}";
        assertEquals(expected, predicate.toString());
    }
}
