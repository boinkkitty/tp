package seedu.address.logic.parser;

import seedu.address.logic.commands.PurgeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a new {@code PurgeCommand}.
 * <p>
 * This command does not accept any arguments. If any extra arguments are provided,
 * a {@code ParseException} is thrown.
 */
public class PurgeCommandParser implements Parser<PurgeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PurgeCommand
     * and returns a PurgeCommand object for execution.
     *
     * @param args user input arguments
     * @return a new {@code PurgeCommand}
     * @throws ParseException if any non-empty arguments are provided
     */
    @Override
    public PurgeCommand parse(String args) throws ParseException {

        if (!args.trim().isEmpty()) {
            throw new ParseException("Purge command does not take any arguments!");
        }
        return new PurgeCommand();
    }
}
