package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    // Use the typical address book for testing
    private final Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void execute_sortUnfilteredList_success() {
        // The command should reorder the typical Model in alphabetical order
        SortCommand sortCommand = new SortCommand();

        // We manually sort the expectedModel’s list by name (ignore case)
        List<Person> sortedList = new ArrayList<>(expectedModel.getAddressBook().getPersonList());
        sortedList.sort((p1, p2) ->
                p1.getName().fullName.compareToIgnoreCase(p2.getName().fullName));

        // Overwrite expectedModel with the sorted list
        AddressBook sortedAb = new AddressBook();
        sortedList.forEach(sortedAb::addPerson);
        expectedModel.setAddressBook(sortedAb);

        String expectedMessage = SortCommand.MESSAGE_SORT_SUCCESS;

        // Now check that the command sorts model to match expectedModel
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

        AddressBook singleAb = new AddressBook();
        singleAb.addPerson(singlePerson);

        Model singleModel = new ModelManager(singleAb, new UserPrefs());
        Model expectedSingleModel = new ModelManager(singleAb, new UserPrefs());

        SortCommand sortCommand = new SortCommand();

        // Execute command
        CommandResult commandResult = sortCommand.execute(singleModel);

        // Verify
        assertEquals(SortCommand.MESSAGE_SORT_SUCCESS, commandResult.getFeedbackToUser());
        // Single-person list won't change
        assertEquals(expectedSingleModel, singleModel);
    }

    @Test
    public void execute_alreadySortedList_noChangeButSuccess() {
        // If the list is already sorted, the command doesn't change anything

        // 1) Sort expectedModel first
        List<Person> sortedList = new ArrayList<>(expectedModel.getAddressBook().getPersonList());
        sortedList.sort((p1, p2) ->
                p1.getName().fullName.compareToIgnoreCase(p2.getName().fullName));
        AddressBook sortedAb = new AddressBook();
        sortedList.forEach(sortedAb::addPerson);
        expectedModel.setAddressBook(sortedAb);

        // 2) Use the same sorted AB for the real model
        Model sortedModel = new ModelManager(sortedAb, new UserPrefs());

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

        // Sorting an empty model
        assertCommandSuccess(sortCommand, emptyModel, Messages.MESSAGE_NO_PERSON_TO_SORT,
                new ModelManager(new AddressBook(), new UserPrefs()));


        Person newPerson = new PersonBuilder().withName("Aaron").build();
        emptyModel.addPerson(newPerson);

    }

    @Test
    public void parse_nonEmptyArgs_throwsParseException() {
        // "sort" should have no arguments; providing any (e.g., "name") should fail
        assertThrows(ParseException.class, () -> parser.parse(" name"));
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
}
