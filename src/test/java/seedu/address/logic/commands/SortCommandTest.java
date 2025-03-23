package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private final Model model = new ModelManager(createUnsortedAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(createUnsortedAddressBook(), new UserPrefs());

    @Test
    public void execute_sortUnfilteredList_success() {
        // The command should reorder the unsorted Model in alphabetical order
        SortCommand sortCommand = new SortCommand();

        // We manually create what the sorted list should look like
        List<Person> sortedList = new ArrayList<>(expectedModel.getAddressBook().getPersonList());
        // e.g. sort them by name:
        sortedList.sort((p1, p2) -> p1.getName().fullName.compareToIgnoreCase(p2.getName().fullName));

        // Now we set the sorted list into expectedModel
        expectedModel.setAddressBook(createAddressBookFromList(sortedList));

        String expectedMessage = SortCommand.MESSAGE_SORT_SUCCESS;

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortEmptyList_success() {
        // If there's no persons, sorting does nothing but should succeed
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        Model expectedEmptyModel = new ModelManager(new AddressBook(), new UserPrefs());

        SortCommand sortCommand = new SortCommand();
        String expectedMessage = Messages.MESSAGE_NO_PERSON_TO_SORT;

        // Should do nothing but still succeed
        assertCommandSuccess(sortCommand, emptyModel, expectedMessage, expectedEmptyModel);
    }

    @Test
    public void execute_sortSinglePerson_success() throws CommandException {
        // Sorting a single-person list does nothing but should still succeed
        Person singlePerson = new PersonBuilder().withName("Zoe").build();
        AddressBook singleAb = createAddressBookFromList(Arrays.asList(singlePerson));
        Model singleModel = new ModelManager(singleAb, new UserPrefs());
        Model expectedSingleModel = new ModelManager(singleAb, new UserPrefs());

        SortCommand sortCommand = new SortCommand();

        // Execute command
        CommandResult commandResult = sortCommand.execute(singleModel);

        // Check the final result
        assertEquals(SortCommand.MESSAGE_SORT_SUCCESS, commandResult.getFeedbackToUser());
        // Single-person list won't change
        assertEquals(expectedSingleModel, singleModel);
    }

    @Test
    public void execute_alreadySortedList_noChangeButSuccess() {
        // If the list is already sorted, the command doesn't change anything
        // We'll reuse 'expectedModel' but pre-sort it
        List<Person> sortedList = new ArrayList<>(expectedModel.getAddressBook().getPersonList());
        sortedList.sort((p1, p2) -> p1.getName().fullName.compareToIgnoreCase(p2.getName().fullName));
        expectedModel.setAddressBook(createAddressBookFromList(sortedList));

        Model sortedModel = new ModelManager(createAddressBookFromList(sortedList), new UserPrefs());

        SortCommand sortCommand = new SortCommand();
        String expectedMessage = SortCommand.MESSAGE_SORT_SUCCESS;

        // The final state is the same
        assertCommandSuccess(sortCommand, sortedModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noPersonsToSort_noPersonsInModelAfterAdd() {
        // Example test that shows newly added person won't reorder automatically

        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        SortCommand sortCommand = new SortCommand();
        assertCommandSuccess(sortCommand, emptyModel, Messages.MESSAGE_NO_PERSON_TO_SORT,
                new ModelManager(new AddressBook(), new UserPrefs()));

        Person newPerson = new PersonBuilder().withName("Aaron").build();
        emptyModel.addPerson(newPerson);

    }

    @Test
    public void equals() {
        // SortCommand has no fields, so all instances are effectively the same
        SortCommand sortCommand1 = new SortCommand();
        SortCommand sortCommand2 = new SortCommand();

        // same object -> returns true
        assertTrue(sortCommand1.equals(sortCommand1));

        // another instance -> also returns true (no fields to compare)
        assertTrue(sortCommand1.equals(sortCommand2));

        // null -> returns false
        assertFalse(sortCommand1.equals(null));

        // different type -> returns false
        assertFalse(sortCommand1.equals(5)); // e.g. an integer
    }

    // ------------------ Helper Methods --------------------------------

    /**
     * Creates a small unsorted AddressBook for testing. Adjust the size/names as you wish.
     */
    private static AddressBook createUnsortedAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addPerson(new PersonBuilder().withName("Charlie").build());
        ab.addPerson(new PersonBuilder().withName("alice").build()); // test ignoring case
        ab.addPerson(new PersonBuilder().withName("Bob Choo").build());
        return ab;
    }

    /**
     * Creates an AddressBook from a given list of Persons.
     */
    private static AddressBook createAddressBookFromList(List<Person> persons) {
        AddressBook ab = new AddressBook();
        persons.forEach(ab::addPerson);
        return ab;
    }
}
