package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a new {@code SortCommand}.
 * <p>
 * This command does not accept any arguments. If any extra arguments are provided,
 * a {@code ParseException} is thrown.
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @param args user input arguments
     * @return a new {@code SortCommand}
     * @throws ParseException if any non-empty arguments are provided
     */
    @Override
    public SortCommand parse(String args) throws ParseException {

        if (!args.trim().isEmpty()) {
            throw new ParseException("Sort command does not take any arguments!");
        }
        return new SortCommand();
    }
}
