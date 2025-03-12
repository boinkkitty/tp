package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_DATE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class PaymentCommand extends Command {

    public static final String COMMAND_WORD = "payment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the payment information"
            + " of the person identified "
            + "by the index number used in the person listing. "
            + "Existing payment  will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_FEE + "FEE [" + PREFIX_PAYMENT_DATE + "PAYMENT_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_FEE + "900 " + PREFIX_PAYMENT_DATE + "13-03-2025";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Payment command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
