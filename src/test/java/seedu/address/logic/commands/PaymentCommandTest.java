package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_FEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

public class PaymentCommandTest {
    private Model model = new ModelManager(new AddressBookBuilder().withPerson(new PersonBuilder().build()).build(),
            new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        PaymentCommand paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE,
                VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);

        String expectedMessage = String.format(PaymentCommand.MESSAGE_ADD_PAYMENT_SUCCESS,
                new PersonBuilder().withPaymentFee(VALID_PAYMENT_FEE).withPaymentDate(VALID_PAYMENT_DATE)
                        .withPaymentStatus(VALID_PAYMENT_STATUS).build());

        Model expectedModel = new ModelManager(new AddressBookBuilder().withPerson(
                new PersonBuilder().build()).build(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0),
                new PersonBuilder().withPaymentFee(VALID_PAYMENT_FEE).withPaymentDate(VALID_PAYMENT_DATE)
                        .withPaymentStatus(VALID_PAYMENT_STATUS).build());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_eitherFieldSpecified_success() {
        // `paymentFee` specified only
        PaymentCommand paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, "", "");

        String expectedMessage = String.format(PaymentCommand.MESSAGE_ADD_PAYMENT_SUCCESS,
                new PersonBuilder().withPaymentFee(VALID_PAYMENT_FEE).build());

        Model expectedModel = new ModelManager(new AddressBookBuilder().withPerson(
                new PersonBuilder().build()).build(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0),
                new PersonBuilder().withPaymentFee(VALID_PAYMENT_FEE).build());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);

        // `paymentDate` specified only
        paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, 0, VALID_PAYMENT_DATE, "");

        expectedMessage = String.format(PaymentCommand.MESSAGE_UPDATE_PAYMENT_SUCCESS,
                new PersonBuilder().withPaymentDate(VALID_PAYMENT_DATE).build());

        expectedModel.setPerson(model.getFilteredPersonList().get(0),
                new PersonBuilder().withPaymentDate(VALID_PAYMENT_DATE).build());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);

        // `paymentStatus` specified only
        paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, 0, "", VALID_PAYMENT_STATUS);

        expectedMessage = String.format(PaymentCommand.MESSAGE_UPDATE_PAYMENT_SUCCESS,
                new PersonBuilder().withPaymentStatus(VALID_PAYMENT_STATUS).build());

        expectedModel.setPerson(model.getFilteredPersonList().get(0),
                new PersonBuilder().withPaymentStatus(VALID_PAYMENT_STATUS).build());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_messageResponseCheck_success() {
        // `MESSAGE_ADD_PAYMENT_SUCCESS` check
        PaymentCommand paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE,
                VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);

        String expectedMessage = String.format(PaymentCommand.MESSAGE_ADD_PAYMENT_SUCCESS,
                new PersonBuilder().withPaymentFee(VALID_PAYMENT_FEE).withPaymentDate(VALID_PAYMENT_DATE)
                        .withPaymentStatus(VALID_PAYMENT_STATUS).build());

        Model expectedModel = new ModelManager(new AddressBookBuilder().withPerson(
                new PersonBuilder().build()).build(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0),
                new PersonBuilder().withPaymentFee(VALID_PAYMENT_FEE).withPaymentDate(VALID_PAYMENT_DATE)
                        .withPaymentStatus(VALID_PAYMENT_STATUS).build());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);

        // `MESSAGE_UPDATE_PAYMENT_SUCCESS` check
        paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, "", "");

        expectedMessage = String.format(PaymentCommand.MESSAGE_UPDATE_PAYMENT_SUCCESS,
                new PersonBuilder().withPaymentFee(VALID_PAYMENT_FEE).build());

        expectedModel.setPerson(model.getFilteredPersonList().get(0),
                new PersonBuilder().withPaymentFee(VALID_PAYMENT_FEE).build());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);

        // `MESSAGE_DELETE_PAYMENT_SUCCESS` check
        paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, 0, "", "");

        expectedMessage = String.format(PaymentCommand.MESSAGE_DELETE_PAYMENT_SUCCESS, new PersonBuilder().build());

        expectedModel.setPerson(model.getFilteredPersonList().get(0), new PersonBuilder().build());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);

        // `MESSAGE_SAME_PAYMENT_SUCCESS` check
        paymentCommand = new PaymentCommand(INDEX_FIRST_PERSON, 0, "", "");

        expectedMessage = String.format(PaymentCommand.MESSAGE_SAME_PAYMENT_SUCCESS, new PersonBuilder().build());

        expectedModel.setPerson(model.getFilteredPersonList().get(0), new PersonBuilder().build());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PaymentCommand paymentCommand = new PaymentCommand(outOfBoundIndex, VALID_PAYMENT_FEE,
                VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);

        assertCommandFailure(paymentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        model.addPerson(new PersonBuilder().withName("Benny").withPhone("12345678").withEmail("benny@hotmail.com")
                .withAddress("Singapore somewhere...").build());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        PaymentCommand paymentCommand = new PaymentCommand(outOfBoundIndex, VALID_PAYMENT_FEE,
                VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);

        assertCommandFailure(paymentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PaymentCommand standardCommand =
                new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);

        // same values -> returns true
        PaymentCommand commandWithSameValues =
                new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> return false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> return false
        assertFalse(standardCommand.equals(
                new PaymentCommand(INDEX_SECOND_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS)));

        // different paymentFee -> return false
        assertFalse(standardCommand.equals(
                new PaymentCommand(INDEX_FIRST_PERSON, 900, VALID_PAYMENT_DATE, VALID_PAYMENT_STATUS)));

        // different paymentDate -> return false
        assertFalse(standardCommand.equals(
                new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, "13-12-2000", VALID_PAYMENT_STATUS)));

        // different paymentStatus -> return false
        assertFalse(standardCommand.equals(
                new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE, "PAID")));
    }
}
