package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_FEE;

import seedu.address.commons.core.index.Index;
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
            + PREFIX_PAYMENT_FEE + "FEE [" + PREFIX_PAYMENT_DATE + "PAYMENT_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PAYMENT_FEE + "900 " + PREFIX_PAYMENT_DATE + "13-03-2025";
    public static final String MESSAGE_PAYMENT_SUCCESS = "Payment Fee: %1$d, Payment Date: %2$s";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Payment command not implemented yet";

    private final Index index;
    private final int paymentFee;
    private final String paymentDate;

    /**
     * Creates a PaymentCommand to update a specific {@code Person} using {@code index}.
     *
     * @param index of the person in the filtered person list to edit the remark
     * @param paymentFee of the person to be updated to
     * @param paymentDate of the person to be updated to
     */
    public PaymentCommand(Index index, int paymentFee, String paymentDate) {
        requireAllNonNull(index, paymentFee, paymentDate);

        this.index = index;
        this.paymentFee = paymentFee;
        this.paymentDate = paymentDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PaymentCommand)) {
            return false;
        }

        PaymentCommand e = (PaymentCommand) other;
        return index.equals(e.index)
                && paymentFee == e.paymentFee
                && paymentDate.equals(e.paymentDate);
    }
}
