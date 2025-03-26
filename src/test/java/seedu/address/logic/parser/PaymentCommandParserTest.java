package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_FEE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_FEE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_FEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PaymentCommand;
import seedu.address.model.person.PaymentInfo;

public class PaymentCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaymentCommand.MESSAGE_USAGE);
    private PaymentCommandParser parser = new PaymentCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // no optional fields provided
        String userInput = String.valueOf(INDEX_FIRST_PERSON.getOneBased());
        PaymentCommand expectedCommand = new PaymentCommand(INDEX_FIRST_PERSON, 0, "", "");
        assertParseSuccess(parser, userInput, expectedCommand);

        // have fee only
        userInput = INDEX_FIRST_PERSON.getOneBased() + PAYMENT_FEE_DESC;
        expectedCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, "", "");
        assertParseSuccess(parser, userInput, expectedCommand);

        // have date only
        userInput = INDEX_FIRST_PERSON.getOneBased() + PAYMENT_DATE_DESC;
        expectedCommand = new PaymentCommand(INDEX_FIRST_PERSON, 0, "13-03-2025", "");
        assertParseSuccess(parser, userInput, expectedCommand);

        // have status only
        userInput = INDEX_FIRST_PERSON.getOneBased() + PAYMENT_STATUS_DESC;
        expectedCommand = new PaymentCommand(INDEX_FIRST_PERSON, 0, "", VALID_PAYMENT_STATUS);
        assertParseSuccess(parser, userInput, expectedCommand);

        // have fee, date and status
        userInput = INDEX_FIRST_PERSON.getOneBased() + PAYMENT_FEE_DESC + PAYMENT_DATE_DESC + PAYMENT_STATUS_DESC;
        expectedCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE,
                VALID_PAYMENT_DATE, "Waiting");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid fee
        assertParseFailure(parser, "1" + INVALID_PAYMENT_FEE_DESC, PaymentInfo.MESSAGE_CONSTRAINTS_FEE);

        // invalid date
        assertParseFailure(parser, "1" + INVALID_PAYMENT_DATE_DESC, PaymentInfo.MESSAGE_CONSTRAINTS_DATE);

        // invalid status
        assertParseFailure(parser, "1" + INVALID_PAYMENT_STATUS_DESC, PaymentInfo.MESSAGE_CONSTRAINTS_STATUS);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaymentCommand.MESSAGE_USAGE);

        // Missing index & fields
        assertParseFailure(parser, PaymentCommand.COMMAND_WORD, expectedMessage);

        // Missing index with fields provided
        assertParseFailure(parser, PaymentCommand.COMMAND_WORD + PAYMENT_FEE_DESC + PAYMENT_DATE_DESC,
                expectedMessage);
    }
}
