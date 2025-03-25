package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Purges the address book.
 */
public class PurgeCommand extends Command {

    public static final String COMMAND_WORD = "purge";
    public static final String MESSAGE_SUCCESS = "Address book has been purged!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
