package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.ClearCommand.MESSAGE_INVALID_INDEX_RANGE;
import static seedu.address.logic.commands.ClearCommand.MESSAGE_NO_PERSONS_FOUND;
import static seedu.address.logic.commands.ClearCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.ClearCommand.MESSAGE_SUCCESS_INDEX;
import static seedu.address.logic.commands.ClearCommand.MESSAGE_SUCCESS_TAG;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ClearCommand}.
 */
public class ClearCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_validIndexRange_success() {
        // Simulate clearing by valid index range (start < end)
        ClearCommand clearCommand = new ClearCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        String innerMessage = String.format(MESSAGE_SUCCESS_INDEX,
                2, INDEX_FIRST_PERSON.getOneBased(), INDEX_SECOND_PERSON.getOneBased());
        String expectedMessage = String.format(MESSAGE_SUCCESS, innerMessage);
        // Delete persons in the range [1, 2] (inclusive) on the expected model

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Person> personsToRemove = expectedModel.getFilteredPersonList()
                .subList(INDEX_FIRST_PERSON.getZeroBased(), INDEX_SECOND_PERSON.getZeroBased() + 1)
                .stream().toList();

        personsToRemove.forEach(expectedModel::deletePerson);
        assertCommandSuccess(clearCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexRange_throwsCommandException() {
        // Simulate clearing with an invalid range where start index is not strictly less than end index
        ClearCommand clearCommand = new ClearCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);

        assertCommandFailure(clearCommand, model, MESSAGE_INVALID_INDEX_RANGE);
    }

    @Test
    public void execute_validTag_success() {
        // Simulate clearing by tag
        Set<Tag> targetTags = new HashSet<>();
        targetTags.add(new Tag("CS2030C"));

        ClearCommand clearCommand = new ClearCommand(targetTags);
        String expectedMessage = String
                .format(MESSAGE_SUCCESS,
                        String.format(MESSAGE_SUCCESS_TAG, 3, targetTags.toString()));

        // Simulate tag-based deletion results for the expected model
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.getFilteredPersonList().stream()
                .filter(person -> person.getTags().contains(new Tag("CS2030C")))
                .forEach(expectedModel::deletePerson);

        assertCommandSuccess(clearCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMatchingTags_success() {
        // Simulate clearing when no persons match the provided tag
        Set<Tag> nonMatchingTags = new HashSet<>();
        nonMatchingTags.add(new Tag("nonexist"));

        ClearCommand clearCommand = new ClearCommand(nonMatchingTags);
        String expectedMessage = String.format(MESSAGE_SUCCESS,
                String.format(MESSAGE_NO_PERSONS_FOUND, nonMatchingTags.toString()));

        assertCommandSuccess(clearCommand, model, expectedMessage, model);
    }

    @Test
    public void constructor_nullInputs_throwsNullPointerException() {
        // Null constructor parameters should throw NullPointerException
        assertThrows(NullPointerException.class, () -> new ClearCommand(null));
        assertThrows(NullPointerException.class, () -> new ClearCommand(null, null));
    }

    @Test
    public void equals() {
        // Commands with index-based clearing
        ClearCommand clearByIndexCommand1 = new ClearCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        ClearCommand clearByIndexCommand2 = new ClearCommand(INDEX_FIRST_PERSON, INDEX_THIRD_PERSON);

        // Commands with tag-based clearing
        Set<Tag> targetTags1 = new HashSet<>();
        targetTags1.add(new Tag("CS2030C"));
        ClearCommand clearByTagCommand1 = new ClearCommand(targetTags1);

        Set<Tag> targetTags2 = new HashSet<>();
        targetTags2.add(new Tag("GEA1000"));
        ClearCommand clearByTagCommand2 = new ClearCommand(targetTags2);

        // Same object => true
        assertEquals(clearByIndexCommand1, clearByIndexCommand1);

        // Same values for index-based command => true
        ClearCommand clearByIndexCommand1Copy = new ClearCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        assertEquals(clearByIndexCommand1, clearByIndexCommand1Copy);

        // Same values for tag-based command => true
        Set<Tag> targetTags1Copy = new HashSet<>();
        targetTags1Copy.add(new Tag("CS2030C"));
        ClearCommand clearByTagCommand1Copy = new ClearCommand(targetTags1Copy);
        assertEquals(clearByTagCommand1, clearByTagCommand1Copy);

        // Different types => false
        assertNotEquals(1, clearByIndexCommand1);

        // Null => false
        assertNotEquals(null, clearByIndexCommand1);

        // Different index-based commands (start and end indexes differ) => false
        assertNotEquals(clearByIndexCommand1, clearByIndexCommand2);

        // Different tag-based commands (different tags) => false
        assertNotEquals(clearByTagCommand1, clearByTagCommand2);

        // Index-based command vs tag-based command => false
        assertNotEquals(clearByIndexCommand1, clearByTagCommand1);
    }


    @Test
    public void toStringMethod() {
        Set<Tag> targetTags = new HashSet<>();
        targetTags.add(new Tag("CS2030C"));

        ClearCommand clearByTagCommand = new ClearCommand(targetTags);
        ClearCommand clearByIndexCommand = new ClearCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        assertEquals(ClearCommand.class.getCanonicalName() + "{tags=[[CS2030C]]}",
                clearByTagCommand.toString());
        assertEquals(ClearCommand.class.getCanonicalName() + "{start=1, end=2}",
                clearByIndexCommand.toString());
    }
}
