package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Sorts the current list of persons in the address book in alphabetical order by name.
 * Subsequent new persons are appended at the bottom (i.e., not automatically resorted).
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons by name in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SORT_SUCCESS = "Sorted all persons by name.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getAddressBook().getPersonList().isEmpty()) {
            return new CommandResult(Messages.MESSAGE_NO_PERSON_TO_SORT);
        }
        model.sortPersonsByName();
        return new CommandResult(MESSAGE_SORT_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {

        return other == this
                || (other instanceof SortCommand);
    }
}
