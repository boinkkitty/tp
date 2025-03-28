package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a new {@code HelpCommand}.
 * <p>
 * This command does not accept any arguments. If any extra arguments are provided,
 * a {@code ParseException} is thrown.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     *
     * @param args user input arguments
     * @return a new {@code HelpCommand}
     * @throws ParseException if any non-empty arguments are provided
     */
    @Override
    public HelpCommand parse(String args) throws ParseException {

        if (!args.trim().isEmpty()) {
            throw new ParseException("Help command does not take any arguments!");
        }
        return new HelpCommand();
    }
}
