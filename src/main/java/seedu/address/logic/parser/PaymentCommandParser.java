package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_STATUS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PaymentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PaymentCommand object
 */
public class PaymentCommandParser implements Parser<PaymentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PaymentCommand
     * and returns a PaymentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PaymentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PAYMENT_FEE, PREFIX_PAYMENT_DATE, PREFIX_PAYMENT_STATUS);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaymentCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaymentCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PAYMENT_FEE, PREFIX_PAYMENT_DATE, PREFIX_PAYMENT_STATUS);

        int paymentFee;
        if (argMultimap.getValue(PREFIX_PAYMENT_FEE).isPresent()) {
            paymentFee = ParserUtil.parseFee(argMultimap.getValue(PREFIX_PAYMENT_FEE).get());
        } else {
            paymentFee = 0;
        }

        String paymentDate;
        if (argMultimap.getValue(PREFIX_PAYMENT_DATE).isPresent()) {
            paymentDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_PAYMENT_DATE).get());
        } else {
            paymentDate = "";
        }

        String paymentStatus;
        if (argMultimap.getValue(PREFIX_PAYMENT_STATUS).isPresent()) {
            paymentStatus = ParserUtil.parsePaymentStatus(argMultimap.getValue(PREFIX_PAYMENT_STATUS).get());
        } else {
            paymentStatus = "";
        }

        return new PaymentCommand(index, paymentFee, paymentDate, paymentStatus);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
