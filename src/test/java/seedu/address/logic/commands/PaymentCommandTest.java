package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_FEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PaymentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Person defaultPerson = new PersonBuilder().build();
        PaymentCommand paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE);

        String expectedMessage = String.format(PaymentCommand.MESSAGE_PAYMENT_SUCCESS,
                VALID_PAYMENT_FEE, VALID_PAYMENT_DATE);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPerson(defaultPerson);
        // Add payment info here to expectedModel
        // ...
        // assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final PaymentCommand standardCommand =
                new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE);

        // same values -> returns true
        PaymentCommand commandWithSameValues =
                new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> return false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // differnet index -> return false
        assertFalse(standardCommand.equals(
                new PaymentCommand(INDEX_SECOND_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE)));

        // different paymentFee -> return false
        assertFalse(standardCommand.equals(
                new PaymentCommand(INDEX_FIRST_PERSON, 900, VALID_PAYMENT_DATE)));

        // different paymentDate -> return false
        assertFalse(standardCommand.equals(
                new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, "13-12-2000")));
    }
}
