package seedu.address.logic.parser;

import seedu.address.logic.commands.ToggleThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a new {@code ToggleThemeCommand}.
 * <p>
 * This command does not accept any arguments. If any extra arguments are provided,
 * a {@code ParseException} is thrown.
 */
public class ToggleThemeCommandParser implements Parser<ToggleThemeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ToggleThemeCommand
     * and returns a ToggleThemeCommand object for execution.
     *
     * @param args user input arguments
     * @return a new {@code ToggleThemeCommand}
     * @throws ParseException if any non-empty arguments are provided
     */
    @Override
    public ToggleThemeCommand parse(String args) throws ParseException {

        if (!args.trim().isEmpty()) {
            throw new ParseException("Toggle Theme command does not take any arguments!");
        }
        return new ToggleThemeCommand();
    }
}
