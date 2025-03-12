package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_FEE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT_FEE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PaymentCommand;

public class PaymentCommandParserTest {
    private PaymentCommandParser parser = new PaymentCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // have fee only
        String userInput = INDEX_FIRST_PERSON.getOneBased() + PAYMENT_FEE_DESC;
        PaymentCommand expectedCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, "");
        assertParseSuccess(parser, userInput, expectedCommand);

        // have fee and date
        userInput = INDEX_FIRST_PERSON.getOneBased() + PAYMENT_FEE_DESC + PAYMENT_DATE_DESC;
        expectedCommand = new PaymentCommand(INDEX_FIRST_PERSON, VALID_PAYMENT_FEE, VALID_PAYMENT_DATE);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaymentCommand.MESSAGE_USAGE);

        // Missing parameters
        assertParseFailure(parser, PaymentCommand.COMMAND_WORD, expectedMessage);

        // Missing index
        assertParseFailure(parser, PaymentCommand.COMMAND_WORD + PAYMENT_FEE_DESC + PAYMENT_DATE_DESC,
                expectedMessage);

        // Missing fee
        assertParseFailure(parser, PaymentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON + PAYMENT_DATE_DESC,
                expectedMessage);
    }
}
