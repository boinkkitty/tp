package seedu.address.logic.parser;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a new {@code ExitCommand}.
 * <p>
 * This command does not accept any arguments. If any extra arguments are provided,
 * a {@code ParseException} is thrown.
 */
public class ExitCommandParser implements Parser<ExitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExitCommand
     * and returns a ExitCommand object for execution.
     *
     * @param args user input arguments
     * @return a new {@code ExitCommand}
     * @throws ParseException if any non-empty arguments are provided
     */
    @Override
    public ExitCommand parse(String args) throws ParseException {

        if (!args.trim().isEmpty()) {
            throw new ParseException("Exit command does not take any arguments!");
        }
        return new ExitCommand();
    }
}
