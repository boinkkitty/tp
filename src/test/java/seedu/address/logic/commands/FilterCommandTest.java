package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.FilterDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterCommand.
 */
public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noFieldSpecifiedFilteredList_success() {
        String expectedMessage = String.format(FilterCommand.MESSAGE_FILTER_SUCCESS, 7);
        FilterDescriptor filterDescriptor = new FilterDescriptor();
        FilterCommand filterCommand = new FilterCommand(filterDescriptor);
        expectedModel.updateFilteredPersonList(filterDescriptor);

        assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_someFieldsSpecifiedFilteredList_success() {
        String expectedMessage = String.format(FilterCommand.MESSAGE_FILTER_SUCCESS, 2);
        FilterDescriptor filterDescriptor = new FilterDescriptorBuilder().withEduLevel("Bachelor")
                .withExpectedGrade("A").build();
        FilterCommand filterCommand = new FilterCommand(filterDescriptor);
        expectedModel.updateFilteredPersonList(filterDescriptor);

        assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());
    }

    /**
     * Filter
     */
    @Test
    public void execute_test_success() {
        FilterDescriptor descriptor = new FilterDescriptorBuilder().withExpectedGrade("A").build();
        FilterCommand filterCommand = new FilterCommand(descriptor);

        expectedModel.updateFilteredPersonList(descriptor);
        String expectedMessage = String.format(FilterCommand.MESSAGE_FILTER_SUCCESS, 2);
        assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        FilterDescriptor descriptor = new FilterDescriptorBuilder().withEduLevel("Primary")
                .withCurrentGrade("B").withExpectedGrade("C").withTags("Tag").build();
        final FilterCommand filterCommand = new FilterCommand(descriptor);

        // same values -> returns true
        FilterDescriptor copyDescriptor = new FilterDescriptor(descriptor);
        FilterCommand commandWithSameValues = new FilterCommand(copyDescriptor);
        assertTrue(filterCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(filterCommand.equals(filterCommand));

        // null -> returns false
        assertFalse(filterCommand.equals(null));

        // different types -> returns false
        assertFalse(filterCommand.equals(new PurgeCommand()));

        // different descriptor -> returns false
        FilterDescriptor differentDescriptor = new FilterDescriptorBuilder().withEduLevel("Secondary").build();
        assertFalse(filterCommand.equals(new FilterCommand(differentDescriptor)));
    }

    @Test
    public void toStringMethod() {
        FilterDescriptor filterDescriptor = new FilterDescriptor();
        FilterCommand filterCommand = new FilterCommand(filterDescriptor);
        String expected = FilterCommand.class.getCanonicalName() + "{filterDescriptor=" + filterDescriptor + "}";
        assertEquals(expected, filterCommand.toString());
    }

}
