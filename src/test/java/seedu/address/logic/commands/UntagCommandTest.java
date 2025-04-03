package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class UntagCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_allTagsExist_tagsRemovedSuccessfully() throws CommandException {
        Person aliceWithTags = new PersonBuilder(ALICE).withTags("CS2040", "Math").build();
        Person bobWithTags = new PersonBuilder(BOB).withTags("CS2040").build();
        model.addPerson(aliceWithTags);
        model.addPerson(bobWithTags);

        Set<Tag> tagsToRemove = Set.of(new Tag("CS2040"));
        UntagCommand untagCommand = new UntagCommand(tagsToRemove);

        String expectedMessage = String.format(UntagCommand.MESSAGE_SUCCESS, tagsToRemove, 2);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person updatedAlice = new PersonBuilder(ALICE).withTags("Math").build();
        Person updatedBob = new PersonBuilder(BOB).withTags().build();
        expectedModel.setPerson(aliceWithTags, updatedAlice);
        expectedModel.setPerson(bobWithTags, updatedBob);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someTagsNotFound_tagsRemovedSuccessfully() throws CommandException {
        Person aliceWithTags = new PersonBuilder(ALICE).withTags("CS2040", "Math").build();
        model.addPerson(aliceWithTags);

        Set<Tag> tagsToRemove = Set.of(new Tag("CS2040"), new Tag("Physics"));
        UntagCommand untagCommand = new UntagCommand(tagsToRemove);

        String expectedMessage = String.format(UntagCommand.MESSAGE_SUCCESS, tagsToRemove, 1);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person updatedAlice = new PersonBuilder(ALICE).withTags("Math").build();
        expectedModel.setPerson(aliceWithTags, updatedAlice);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMatchingTags_noPersonsUpdated() throws CommandException {
        Person aliceWithTags = new PersonBuilder(ALICE).withTags("Math").build();
        model.addPerson(aliceWithTags);

        Set<Tag> tagsToRemove = Set.of(new Tag("CS2040"));
        UntagCommand untagCommand = new UntagCommand(tagsToRemove);

        assertCommandFailure(untagCommand, model, UntagCommand.MESSAGE_NO_PERSON_UPDATED);
    }

    @Test
    public void equals() {
        Set<Tag> tagsToRemove = Set.of(new Tag("CS2030"), new Tag("CS2040"));
        Set<Tag> tagsToRemove2 = Set.of(new Tag("CS2040"), new Tag("CS2030"));
        Set<Tag> tagsToRemove3 = Set.of(new Tag("CS2040"), new Tag("Math"));

        UntagCommand untagCommand1 = new UntagCommand(tagsToRemove);
        UntagCommand untagCommand2 = new UntagCommand(tagsToRemove);
        UntagCommand untagCommand3 = new UntagCommand(tagsToRemove2);
        UntagCommand untagCommand4 = new UntagCommand(tagsToRemove3);

        assertTrue(untagCommand1.equals(untagCommand2));
        assertTrue(untagCommand1.equals(untagCommand3));
        assertFalse(untagCommand1.equals(untagCommand4));
    }
}
