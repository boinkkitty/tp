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

        List<Person> sortedList = new ArrayList<>(expectedModel.getAddressBook().getPersonList());
        sortedList.sort((p1, p2) ->
                p1.getName().fullName.compareToIgnoreCase(p2.getName().fullName));
        AddressBook sortedAb = new AddressBook();
        sortedList.forEach(sortedAb::addPerson);
        expectedModel.setAddressBook(sortedAb);
        String expectedMessage = SortCommand.MESSAGE_SORT_SUCCESS;
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortEmptyList_success() {
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        Model expectedEmptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        SortCommand sortCommand = new SortCommand();
        String expectedMessage = Messages.MESSAGE_NO_PERSON_TO_SORT;
        assertCommandSuccess(sortCommand, emptyModel, expectedMessage, expectedEmptyModel);
    }

    @Test
    public void execute_sortSinglePerson_success() throws CommandException {
        Person singlePerson = new PersonBuilder().withName("Zoe").build();
        AddressBook singleAb = new AddressBook();
        singleAb.addPerson(singlePerson);
        Model singleModel = new ModelManager(singleAb, new UserPrefs());
        Model expectedSingleModel = new ModelManager(singleAb, new UserPrefs());
        SortCommand sortCommand = new SortCommand();

        CommandResult commandResult = sortCommand.execute(singleModel);

        assertEquals(SortCommand.MESSAGE_SORT_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(expectedSingleModel, singleModel);
    }

    @Test
    public void execute_alreadySortedList_noChangeButSuccess() {
        List<Person> sortedList = new ArrayList<>(expectedModel.getAddressBook().getPersonList());
        sortedList.sort((p1, p2) ->
                p1.getName().fullName.compareToIgnoreCase(p2.getName().fullName));
        AddressBook sortedAb = new AddressBook();
        sortedList.forEach(sortedAb::addPerson);
        expectedModel.setAddressBook(sortedAb);
        Model sortedModel = new ModelManager(sortedAb, new UserPrefs());

        SortCommand sortCommand = new SortCommand();
        String expectedMessage = SortCommand.MESSAGE_SORT_SUCCESS;
        assertCommandSuccess(sortCommand, sortedModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noPersonsToSort_noPersonsInModelAfterAdd() throws CommandException {
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        SortCommand sortCommand = new SortCommand();
        assertCommandSuccess(sortCommand, emptyModel, Messages.MESSAGE_NO_PERSON_TO_SORT,
                new ModelManager(new AddressBook(), new UserPrefs()));


        Person newPerson = new PersonBuilder().withName("Aaron").build();
        emptyModel.addPerson(newPerson);

    }

    @Test
    public void parse_nonEmptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" name"));
    }

    @Test
    public void equals() {

        SortCommand sortCommand1 = new SortCommand();
        SortCommand sortCommand2 = new SortCommand();
        assertTrue(sortCommand1.equals(sortCommand1));

        assertTrue(sortCommand1.equals(sortCommand2));

        assertFalse(sortCommand1.equals(null));

        assertFalse(sortCommand1.equals(5));
    }
}
